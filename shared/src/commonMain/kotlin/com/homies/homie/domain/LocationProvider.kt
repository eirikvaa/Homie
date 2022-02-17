package com.homies.homie.domain

import com.homies.homie.domain.model.Coordinate
import kotlinx.coroutines.flow.Flow

interface LocationProvider {
    suspend fun getLocationUpdates(): Flow<Coordinate>
}
