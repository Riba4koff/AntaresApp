package com.example.antaresapp.domain.useCase.NewsUseCase

import com.example.antaresapp.domain.models.NewsItem
import com.example.antaresapp.domain.repository.NewsRepository

class SaveItemNewsFeedUseCase(private val newsRepository: NewsRepository) {

    fun execute(itemNewsFeedModel: NewsItem){
        newsRepository.insertNews(itemNewsFeedModel)
    }

}