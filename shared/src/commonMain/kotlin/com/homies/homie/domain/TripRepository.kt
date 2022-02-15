package com.homies.homie.domain

import com.homies.homie.domain.converter.asDomainModel
import com.homies.homie.domain.model.Location
import com.homies.homie.domain.model.Trip
import com.homies.homie.network.api.EnturJourneyPlannerApi
import com.homies.homie.network.api.EnturJourneyPlannerApiImpl
import com.homies.homie.network.api.converter.toApiModel

interface TripRepository {
    @Throws(Exception::class)
    suspend fun getTrip(from: Location, to: Location): Trip?
}

class TripRepositoryImpl : TripRepository {
    private val enturJourneyPlannerApi: EnturJourneyPlannerApi = EnturJourneyPlannerApiImpl()

    override suspend fun getTrip(from: Location, to: Location): Trip? =
        enturJourneyPlannerApi.getTrip(
            from = from.toApiModel(),
            to = to.toApiModel()
        )?.asDomainModel()
}
