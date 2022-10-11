package com.example.antaresapp.domain.repository

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.antaresapp.domain.models.ScreenModel

interface ScreenModelRepository {
    fun saveNews(news: ScreenModel.NewsItem)
    fun deleteNews(newsItem: ScreenModel.NewsItem)
    fun saveSurvey(survey: ScreenModel.SurveyInfo)
    fun deleteSurvey(survey: ScreenModel.SurveyInfo)
    fun get() : SnapshotStateList<ScreenModel>
}