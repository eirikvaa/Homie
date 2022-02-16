package com.homies.homie.domain

import com.homies.homie.domain.model.Location
import com.homies.homie.network.api.EnturGeocoderApi
import com.homies.homie.network.api.EnturGeocoderApiImpl
import com.homies.homie.network.api.converter.asLocations

interface AutocompleteRepository {
    @Throws(Exception::class)
    suspend fun autocomplete(text: String): List<Location>
}

class AutocompleteRepositoryImpl : AutocompleteRepository {
    private val enturGeocoderApi: EnturGeocoderApi = EnturGeocoderApiImpl()

    override suspend fun autocomplete(text: String): List<Location> =
        enturGeocoderApi.autocomplete(
            text = text,
            size = 20,
            lang = "no"
        ).asLocations()
}
