package com.homies.homie.network.api.converter

import com.homies.homie.domain.model.Coordinates
import com.homies.homie.type.InputCoordinates

internal fun Coordinates.toApiModel(): InputCoordinates =
    InputCoordinates(
        latitude = latitude.toDouble(),
        longitude = longitude.toDouble()
    )
