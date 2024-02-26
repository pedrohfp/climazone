package com.accenture.features.example.data

import retrofit2.http.GET
import retrofit2.http.POST

internal interface NewsApi {
    @GET("top-headlines")
    suspend fun loadHeadlines(): TopHeadlineDTO
}