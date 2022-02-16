package com.homies.homie.network.api

import com.homies.homie.network.api.model.AutocompleteResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal interface EnturGeocoderApi {
    @Throws(Exception::class)
    suspend fun autocomplete(
        text: String,
        size: Int,
        lang: String
    ): AutocompleteResponse
}

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

    override suspend fun autocomplete(
        text: String,
        size: Int,
        lang: String
    ): AutocompleteResponse =
        httpClient.get("$BASE_URL/autocomplete") {
            parameter("text", text)
            parameter("size", size)
            parameter("lang", lang)
        }.body()

    private companion object {
        const val BASE_URL = "https://api.entur.io/geocoder/v1"
    }
}
