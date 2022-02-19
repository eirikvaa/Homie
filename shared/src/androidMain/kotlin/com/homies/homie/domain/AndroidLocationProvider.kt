package com.homies.homie.domain

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Looper
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.homies.homie.Platform
import com.homies.homie.domain.model.Coordinate
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

actual interface LocationProvider {
    @SuppressLint("MissingPermission")
    actual suspend fun getLocationUpdates(): Flow<Coordinate> {
        return callbackFlow {
            if (!locationPermissionGranted()) this.close()

            val locationCallback = object : LocationCallback() {
                override fun onLocationResult(p0: LocationResult) {
                    super.onLocationResult(p0)

                    trySendBlocking(
                        Coordinate(
                            p0.lastLocation.longitude.toFloat(),
                            p0.lastLocation.latitude.toFloat()
                        )
                    )
                }
            }

            val locationRequest = LocationRequest.create().apply {
                interval = LOCATION_UPDATE_INTERVAL
                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            }
            LocationServices.getFusedLocationProviderClient(Platform.context)
                .requestLocationUpdates(
                    locationRequest,
                    locationCallback,
                    Looper.getMainLooper()
                )

            awaitClose {
                LocationServices.getFusedLocationProviderClient(Platform.context)
                    .removeLocationUpdates(locationCallback)
            }
        }
    }

    private fun locationPermissionGranted() = ActivityCompat.checkSelfPermission(
        Platform.context,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
        Platform.context,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    companion object {
        const val LOCATION_UPDATE_INTERVAL: Long = 10_000
    }
}
