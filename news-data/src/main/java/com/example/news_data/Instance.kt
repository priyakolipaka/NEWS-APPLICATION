package com.example.news_data

import com.example.news_data.newsapi.NewsApi
import com.example.news_data.repository.NewsRepository
import com.example.news_data.repository.NewsRepositoryImpl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Instance {
    private const val BASE_URL = "https://newsapi.org/v2/"

    fun getNewsRepository(): NewsRepositoryImpl {
        val okHttpClient = OkHttpClient.Builder()
            .build()

            val converterFactory = GsonConverterFactory.create()

            val retrofit = Retrofit.Builder().addConverterFactory(converterFactory).baseUrl(BASE_URL).client(okHttpClient).build()

            val newsApi = retrofit.create(NewsApi::class.java)

            return NewsRepositoryImpl(newsApi)

    }
}