package com.example.news_data.newsapi

import com.example.news_data.model.News
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("everything")
    suspend fun getNewsHeadlines(
        @Query("q") q: String = "google",
        @Query("apiKey") apiKey: String = "670c3a0a0f4b4d7aadf38eb527b36fde"
    ): News

}