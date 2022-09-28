package com.example.antaresapp.domain.useCase.InterviewOptionsUseCase

import com.example.antaresapp.domain.repository.InterviewOptionsRepository

class SaveInterviewOptionUseCase(private val interviewOptionsRepository: InterviewOptionsRepository) {
    fun execute(option : String){
        interviewOptionsRepository.save(option)
    }
}