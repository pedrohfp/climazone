package com.accenture.features.example.data

import com.accenture.features.example.data.ExampleApi

internal class ExampleRepository(private val api: ExampleApi) {

    suspend fun sendSomething(something: String) = api.sendSomething(something)
}