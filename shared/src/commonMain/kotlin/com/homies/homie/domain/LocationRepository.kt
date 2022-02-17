package com.homies.homie.domain

import com.homies.homie.domain.model.Coordinate
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    suspend fun getLocationUpdates(): Flow<Coordinate>
}

class LocationRepositoryImpl(private val locationProvider: LocationProvider) : LocationRepository{
    override suspend fun getLocationUpdates(): Flow<Coordinate> =
        locationProvider.getLocationUpdates()
}
