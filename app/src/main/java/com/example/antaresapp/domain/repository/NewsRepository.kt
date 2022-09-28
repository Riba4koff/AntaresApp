package com.example.antaresapp.domain.repository

import com.example.antaresapp.domain.models.NewsItem

interface NewsRepository {
    fun insertNews(itemNewsFeedList: NewsItem)
    fun getNews() : List<NewsItem>
}