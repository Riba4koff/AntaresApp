package com.example.antaresapp.data.storage.NewsStorage

import com.example.antaresapp.domain.models.NewsItem

class NewsStorageImpl : NewsStorage {

    private val itemNewsfeedList : MutableList<NewsItem> = mutableListOf()

    override fun addItemInList(itemNewsFeedModel: NewsItem) {
        itemNewsfeedList.add(itemNewsFeedModel)
    }

    override fun getList(): List<NewsItem> = itemNewsfeedList

}