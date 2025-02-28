package com.example.assignment2

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json


class RickAndMortyApi {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true }) // Ignore unknown keys
        }
        install(Logging) {
            level = LogLevel.BODY
        }
    }

    suspend fun getCharacters(page: Int): CharacterResponse {
        val url = "https://rickandmortyapi.com/api/character?page=$page"
        return client.get(url).body() // Deserialize JSON directly into CharacterResponse
    }

}
