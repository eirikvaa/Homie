package com.homies.homie.network.api

import com.apollographql.apollo3.ApolloClient
import com.homies.homie.GetTripQuery
import com.homies.homie.type.Location

internal interface EnturJourneyPlannerApi {
    @Throws(Exception::class)
    suspend fun getTrip(from: Location, to: Location): GetTripQuery.Trip?
}

internal class EnturJourneyPlannerApiImpl : EnturJourneyPlannerApi {
    private val client = ApolloClient.Builder()
        .serverUrl(serverUrl = SERVER_URL)
        .build()

    override suspend fun getTrip(from: Location, to: Location): GetTripQuery.Trip? =
        client.query(
            GetTripQuery(
                from = from,
                to = to
            )
        ).execute().data?.trip

    private companion object {
        const val SERVER_URL = "https://api.entur.io/journey-planner/v3/graphql"
    }
}
