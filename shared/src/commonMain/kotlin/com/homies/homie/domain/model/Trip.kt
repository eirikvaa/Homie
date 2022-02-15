package com.homies.homie.domain.model

data class Trip(
    val fromPlace: Place,
    val toPlace: Place,
    val tripPatterns: List<TripPattern>
)
