package com.example.antaresapp.domain.useCase.SurveyOptionsUseCase

import com.example.antaresapp.domain.models.OptionModel
import com.example.antaresapp.domain.repository.SurveyOptionsRepository

class SaveSurveyOptionUseCase(private val surveyOptionsRepository: SurveyOptionsRepository) {
    fun execute(index: Int, option: OptionModel) {
        surveyOptionsRepository.save(index, option)
    }
}