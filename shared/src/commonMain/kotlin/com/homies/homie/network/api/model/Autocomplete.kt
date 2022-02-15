package com.homies.homie.network.api.model

import kotlinx.serialization.Serializable

@Serializable
internal data class AutocompleteResponse(
    val features: List<AutocompleteFeature> = emptyList()
)

@Serializable
internal data class AutocompleteFeature(
    val geometry: AutocompleteGeometry,
    val properties: AutocompleteProperties
)

@Serializable
internal data class AutocompleteGeometry(
    val coordinates: List<Float>
)

@Serializable
internal data class AutocompleteProperties(
    // Looks something like "NSR:StopPlace:58211"
    val id: String,
    // Looks something like "Oslo lufthavn"
    val name: String,
)
