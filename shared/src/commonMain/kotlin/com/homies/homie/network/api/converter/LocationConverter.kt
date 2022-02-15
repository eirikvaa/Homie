package com.homies.homie.network.api.converter

import com.apollographql.apollo3.api.Optional
import com.homies.homie.domain.model.Location

internal fun Location.toApiModel(): com.homies.homie.type.Location =
    com.homies.homie.type.Location(
        name = Optional.presentIfNotNull(name),
        place = Optional.presentIfNotNull(place),
        coordinates = Optional.presentIfNotNull(coordinates?.toApiModel())
    )
