package com.homies.homie.domain.converter

import com.homies.homie.GetTripQuery
import com.homies.homie.domain.model.TripPattern

internal fun GetTripQuery.TripPattern.asDomainModel(): TripPattern = TripPattern(
    expectedStartTime = expectedStartTime,
    expectedEndTime = null
)
