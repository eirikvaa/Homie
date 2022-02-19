package com.homies.homie.domain

import com.homies.homie.domain.model.Coordinate
import kotlinx.coroutines.flow.Flow

expect interface LocationProvider {
    open suspend fun getLocationUpdates(): Flow<Coordinate>
}
