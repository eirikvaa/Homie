package com.homies.homie.domain.model

data class Coordinate(val longitude: Float, val latitude: Float) {
    override fun toString(): String = "($longitude, $latitude)"
}
