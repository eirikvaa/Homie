package com.homies.homie.domain.model

class Coordinate(val longitude: Float, val latitude: Float) {
    constructor(values: List<Float>) : this(values[1], values[0]) {
        require(values.size == 2) {
            "$values needs to be a list of exactly two elements where the first is the latitude " +
                    "and the second is the longitude."
        }
    }

    override fun toString(): String = "($longitude, $latitude)"
}