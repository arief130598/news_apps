package com.arief.news.repository

import com.arief.news.api.ApiNews
import com.arief.news.model.NewsParams
import retrofit2.Response

class ApiNewsRepo(private val apiNews: ApiNews) {

    suspend fun getNews(country: String, category: String, api_key: String): Response<NewsParams> = apiNews.getNews(country, category, api_key)

}