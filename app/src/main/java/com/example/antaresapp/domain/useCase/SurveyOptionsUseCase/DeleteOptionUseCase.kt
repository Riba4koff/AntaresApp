package com.example.antaresapp.domain.useCase.SurveyOptionsUseCase

import com.example.antaresapp.domain.repository.SurveyOptionsRepository

class DeleteOptionUseCase(private val surveyOptionsRepository: SurveyOptionsRepository) {
    fun execute(index : Int){
        surveyOptionsRepository.delete(index)
    }
}