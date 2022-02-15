package com.homies.homie.network.api.converter

import com.homies.homie.domain.model.Coordinate
import com.homies.homie.domain.model.Location
import com.homies.homie.network.api.model.AutocompleteResponse

internal fun AutocompleteResponse.asLocations(): List<Location> = features.map {
    Location(
        name = it.properties.name,
        place = it.properties.id,
        coordinate = Coordinate(values = it.geometry.coordinates)
    )
}
