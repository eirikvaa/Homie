package com.homies.homie.network.api.converter

import com.homies.homie.domain.model.Coordinates
import com.homies.homie.domain.model.Location
import com.homies.homie.network.api.model.AutocompleteResponse

internal fun AutocompleteResponse.asLocations(): List<Location> = features.map {
    Location(
        name = it.properties.name,
        place = it.properties.id,
        coordinates = it.geometry.coordinates.asCoordinates()
    )
}

// This extension is only used in this file, so let's keep it here for now.
internal fun List<Float>.asCoordinates(): Coordinates? {
    // We need a latitude and a longitude, nothing more or less.
    if (size != 2) {
        return null
    }

    // The Entur API dictates that the first element is the longitude and the second element
    // is the latitude, even though it should be the other way around.
    return Coordinates(
        latitude = this[1],
        longitude = this[0]
    )
}
