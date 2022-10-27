package com.arief.news.model

data class NewsParams(
    val status: String = "",
    var totalResults: Int = 0,
    var articles: List<News> = listOf()
)
