package com.homies.homie.network.api.converter

import com.homies.homie.domain.model.Coordinates
import com.homies.homie.domain.model.Location
import com.homies.homie.network.api.model.AutocompleteResponse

internal fun AutocompleteResponse.asLocations(): List<Location> = features.map {
    Location(
        name = it.properties.name,
        place = it.properties.id,
        coordinates = Coordinates(values = it.geometry.coordinates)
    )
}
