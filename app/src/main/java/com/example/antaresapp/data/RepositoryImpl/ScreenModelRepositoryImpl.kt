package com.example.antaresapp.data.RepositoryImpl

import com.example.antaresapp.data.storage.ScreenModelStorage.ScreenModelStorage
import com.example.antaresapp.domain.models.ScreenModel
import com.example.antaresapp.domain.repository.ScreenModelRepository

class ScreenModelRepositoryImpl(private val screenModelStorage: ScreenModelStorage) : ScreenModelRepository {
    override fun saveNews(news: ScreenModel.NewsItem) {
        screenModelStorage.saveNews(news)
    }

    override fun deleteNews(newsItem: ScreenModel.NewsItem) {
        screenModelStorage.deleteNews(newsItem)
    }

    override fun saveSurvey(survey: ScreenModel.SurveyInfo) {
        screenModelStorage.saveSurvey(survey)
    }

    override fun get() = screenModelStorage.get()
    override fun deleteSurvey(survey: ScreenModel.SurveyInfo) {
        screenModelStorage.deleteSurvey(survey)
    }

}