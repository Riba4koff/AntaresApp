package com.example.antaresapp.domain.useCase.InterviewOptionsUseCase

import com.example.antaresapp.domain.repository.InterviewOptionsRepository

class DeleteOptionUseCase(private val interviewOptionsRepository: InterviewOptionsRepository) {
    fun execute(index : Int){
        interviewOptionsRepository.delete(index)
    }
}