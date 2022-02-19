package com.homies.homie.domain

import com.homies.homie.domain.model.Coordinate
import kotlinx.coroutines.flow.Flow

actual class IOSLocationProvider : LocationProvider{
    override suspend fun getLocationUpdates(): Flow<Coordinate> {
        TODO("Not yet implemented")
    }
}