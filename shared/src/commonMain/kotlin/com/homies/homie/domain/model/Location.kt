package com.homies.homie.domain.model

data class Location(
    // Looks something like "Oslo lufthavn"
    val name: String?,
    // Looks something like "NSR:StopPlace:58211"
    val place: String?,
    val coordinate: Coordinate?
)
