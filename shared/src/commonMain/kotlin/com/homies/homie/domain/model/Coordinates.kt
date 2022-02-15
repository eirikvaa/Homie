package com.homies.homie.domain.model

class Coordinates() {
    var longitude: Float = 0.0f
    var latitude: Float = 0.0f

    // TODO: Currently used from iOS, can probably be removed later.
    constructor(longitude: Float, latitude: Float) : this() {
        this.longitude = longitude
        this.latitude = latitude
    }

    /**
     * From the Entur Geocoder API we get coordinates in the form of a list of values.
     * The first element needs to be the longitude and the second the latitude, even though it
     * should be the other way around.
     */
    constructor(values: List<Float>) : this() {
        require(values.size == 2) {
            "$values needs to be a list of exactly two elements where the first is the latitude " +
                "and the second is the longitude."
        }

        this.longitude = values[1]
        this.latitude = values[0]
    }

    override fun toString(): String = "($longitude, $latitude)"
}
