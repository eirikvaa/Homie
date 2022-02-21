// ktlint-disable filename
package com.homies.homie.domain

import com.homies.homie.domain.model.Coordinate
import kotlinx.coroutines.flow.Flow

actual interface LocationProvider {
    actual suspend fun getLocationUpdates(): Flow<Coordinate>
}

class IOSLocationProvider : LocationProvider {
    override suspend fun getLocationUpdates(): Flow<Coordinate> {
        TODO("Not yet implemented")
    }
}
