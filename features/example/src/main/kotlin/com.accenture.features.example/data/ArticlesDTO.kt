package com.accenture.features.example.data

internal data class ArticlesDTO (
    val source: SourceDTO,
    val author: String,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)