package com.homies.homie.network.api

import io.ktor.client.HttpClient
import io.ktor.client.plugins.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal interface EnturGeocoderApi

internal class EnturGeocoderApiImpl : EnturGeocoderApi {
    private val httpClient: HttpClient by lazy {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }

    private companion object {
        const val BASE_URL = "https://api.entur.io/geocoder/v1"
    }
}
