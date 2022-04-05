package com.example.news_data.repository

import com.example.news_data.model.News
import com.example.news_data.newsapi.NewsApi

interface NewsRepository {
    suspend fun getNewsHeadlines(): News
}

class NewsRepositoryImpl(
    private val newsApi: NewsApi
): NewsRepository {
    override suspend fun getNewsHeadlines(): News {
        return newsApi.getNewsHeadlines()
    }
}