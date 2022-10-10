package com.example.antaresapp.domain.useCase.NewsUseCase

import com.example.antaresapp.data.storage.ScreenModelStorage.ScreenModelStorage
import com.example.antaresapp.domain.models.ScreenModel
import com.example.antaresapp.domain.models.ScreenModel.NewsItem
import com.example.antaresapp.domain.repository.ScreenModelRepository

class SaveItemNewsFeedUseCase(private val screenModelRepository: ScreenModelRepository) {

    fun execute(news: ScreenModel.NewsItem){
        screenModelRepository.saveNews(news)
    }

}