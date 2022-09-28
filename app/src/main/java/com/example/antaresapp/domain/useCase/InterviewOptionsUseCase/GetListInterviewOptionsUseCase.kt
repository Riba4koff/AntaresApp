package com.example.antaresapp.domain.useCase.InterviewOptionsUseCase

import com.example.antaresapp.domain.repository.InterviewOptionsRepository

class GetListInterviewOptionsUseCase(private val interviewOptionsRepository: InterviewOptionsRepository) {
    fun execute() = interviewOptionsRepository.getList()
}