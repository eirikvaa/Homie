package com.homies.homie.network.api.converter

import com.homies.homie.domain.model.Coordinate
import com.homies.homie.domain.model.Location
import com.homies.homie.network.api.model.AutocompleteResponse

internal fun AutocompleteResponse.asLocations(): List<Location> = features.map {
    Location(
        name = it.properties.name,
        place = it.properties.id,
        coordinate = Coordinate(
            /**
             * From the Entur Geocoder API we get coordinates in the form of a list of values.
             * The first element needs to be the longitude and the second the latitude, even though it
             * should be the other way around.
             */
            longitude = it.geometry.coordinates[1],
            latitude = it.geometry.coordinates[0]
        )
    )
}
