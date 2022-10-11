package com.example.antaresapp.data.storage.ScreenModelStorage

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.antaresapp.domain.models.ScreenModel

class ScreenModelStorage {
    private val screenModelList = mutableStateListOf<ScreenModel>()

    fun saveNews(news : ScreenModel.NewsItem){
        screenModelList.add(news)
    }
    fun deleteNews(newsItem: ScreenModel.NewsItem){
        screenModelList.remove(newsItem)
    }
    fun saveSurvey(survey: ScreenModel.SurveyInfo){
        screenModelList.add(survey)
    }

    fun deleteSurvey(survey: ScreenModel.SurveyInfo){
        screenModelList.remove(survey)
    }

    fun get() = screenModelList
}