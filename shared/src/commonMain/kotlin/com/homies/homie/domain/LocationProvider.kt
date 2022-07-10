package com.homies.homie.domain

import com.homies.homie.domain.model.Coordinate
import kotlinx.coroutines.flow.Flow

// TODO:
//  We really shouldn't be using this, but I think there is an IDE
//  bug that causes the JVM declaration to be not found. Hopefully
//  we can remove this when we upgrade the Kotlin version.
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect interface LocationProvider {
    suspend fun getLocationUpdates(): Flow<Coordinate>
}
