package com.arief.news.api

import com.arief.news.model.NewsParams
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiNews {

    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String,
    ): Response<NewsParams>

}