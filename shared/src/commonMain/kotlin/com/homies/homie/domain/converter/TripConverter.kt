package com.homies.homie.domain.converter

import com.homies.homie.GetTripQuery
import com.homies.homie.domain.model.Place
import com.homies.homie.domain.model.Trip

internal fun GetTripQuery.Trip.asDomainModel(): Trip = Trip(
    fromPlace = Place(null, null),
    toPlace = Place(null, null),
    tripPatterns = tripPatterns?.mapNotNull { it?.asDomainModel() } ?: emptyList()
)
