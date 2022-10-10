package com.example.antaresapp.data.RepositoryImpl

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.antaresapp.data.storage.SurveyOptionStorage.SurveyOptionsStorage
import com.example.antaresapp.domain.models.OptionModel
import com.example.antaresapp.domain.repository.SurveyOptionsRepository

class SurveyOptionsRepositoryImpl(private val surveyOptionsStorage: SurveyOptionsStorage) :
    SurveyOptionsRepository {
    override fun save(index: Int, option: OptionModel) {
        surveyOptionsStorage.saveOption(index, option)
    }

    override fun getList(): SnapshotStateList<OptionModel> = surveyOptionsStorage.getListOptions()
    override fun delete(index: Int) {
        surveyOptionsStorage.deleteOption(index)
    }
}