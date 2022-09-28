package com.example.antaresapp.domain.useCase.ProjectsUseCase

import com.example.antaresapp.domain.models.InterviewInfo
import com.example.antaresapp.domain.repository.InterviewRepository

class SaveInterviewUseCase(private val interviewRepository: InterviewRepository) {
    fun execute(interviewInfo: InterviewInfo){
        interviewRepository.save(interviewInfo)
    }
}