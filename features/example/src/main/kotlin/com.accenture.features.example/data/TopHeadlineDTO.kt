package com.accenture.features.example.data

internal data class TopHeadlineDTO(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticlesDTO>
)