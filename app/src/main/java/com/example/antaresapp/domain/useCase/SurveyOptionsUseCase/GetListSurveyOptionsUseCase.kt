package com.example.antaresapp.domain.useCase.SurveyOptionsUseCase

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.antaresapp.domain.models.OptionModel
import com.example.antaresapp.domain.repository.SurveyOptionsRepository

class GetListSurveyOptionsUseCase(private val surveyOptionsRepository: SurveyOptionsRepository) {
    fun execute(): SnapshotStateList<OptionModel> {
        return surveyOptionsRepository.getList()
    }
}