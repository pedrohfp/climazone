package com.pagonxt.archref.features.example.data

internal class ExampleRepository(private val api: ExampleApi) {

    suspend fun sendSomething(something: String) = api.sendSomething(something)
}