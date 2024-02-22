package com.pagonxt.archref.features.example.data

import retrofit2.http.Body
import retrofit2.http.POST

internal interface ExampleApi {
    @POST("/example/something")
    suspend fun sendSomething(
        @Body something: String
    ): SomethingDTO
}