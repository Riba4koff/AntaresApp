package com.example.antaresapp.data.RepositoryImpl

import com.example.antaresapp.data.storage.NewsStorage.NewsStorage
import com.example.antaresapp.domain.models.NewsItem
import com.example.antaresapp.domain.repository.NewsRepository

class NewsRepositoryImpl(private val newsStorage: NewsStorage) : NewsRepository {

    override fun insertNews(itemNewsFeedList:NewsItem) {
        newsStorage.addItemInList(itemNewsFeedList)
    }
    override fun getNews() = newsStorage.getList()

}