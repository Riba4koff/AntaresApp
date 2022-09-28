package com.example.antaresapp.domain.useCase.ProjectsUseCase

import com.example.antaresapp.domain.repository.InterviewRepository

class GetListInterviewInfoUseCase(private val interviewRepository: InterviewRepository) {
    fun execute() = interviewRepository.get()
}