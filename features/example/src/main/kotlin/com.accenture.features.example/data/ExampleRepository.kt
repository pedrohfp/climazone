package com.accenture.features.example.data

internal class ExampleRepository(private val api: NewsApi) {

    suspend fun loadHeadlines() = api.loadHeadlines()
}