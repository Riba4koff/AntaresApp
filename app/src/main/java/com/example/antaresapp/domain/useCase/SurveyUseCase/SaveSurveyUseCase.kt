package com.example.antaresapp.domain.useCase.ProjectsUseCase

import com.example.antaresapp.domain.models.ScreenModel
import com.example.antaresapp.domain.models.ScreenModel.SurveyInfo
import com.example.antaresapp.domain.repository.ScreenModelRepository

class SaveSurveyUseCase(private val screenModelRepository: ScreenModelRepository) {
    fun execute(news: ScreenModel.NewsItem){
        screenModelRepository.saveNews(news)
    }
}