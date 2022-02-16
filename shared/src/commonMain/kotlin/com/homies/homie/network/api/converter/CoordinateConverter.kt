package com.homies.homie.network.api.converter

import com.homies.homie.domain.model.Coordinate
import com.homies.homie.type.InputCoordinates

internal fun Coordinate.toApiModel(): InputCoordinates =
    InputCoordinates(
        latitude = latitude.toDouble(),
        longitude = longitude.toDouble()
    )
