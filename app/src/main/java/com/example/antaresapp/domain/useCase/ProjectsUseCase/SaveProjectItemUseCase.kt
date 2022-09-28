package com.example.antaresapp.domain.useCase.ProjectsUseCase

import com.example.antaresapp.domain.models.ProjectItem
import com.example.antaresapp.domain.repository.ProjectsRepository

class SaveProjectItemUseCase(private val projectsRepository: ProjectsRepository) {
    fun execute(projectItem: ProjectItem){
        projectsRepository.insertProject(projectItem)
    }
}