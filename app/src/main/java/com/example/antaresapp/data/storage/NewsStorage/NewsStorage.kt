package com.example.antaresapp.data.storage.NewsStorage

import com.example.antaresapp.domain.models.NewsItem

interface NewsStorage {
    fun addItemInList(itemNewsFeedModel: NewsItem)
    fun getList() : List<NewsItem>
}