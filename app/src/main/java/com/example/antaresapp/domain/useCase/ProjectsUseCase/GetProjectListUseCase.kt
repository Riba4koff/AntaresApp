package com.example.antaresapp.domain.useCase.ProjectsUseCase

import com.example.antaresapp.domain.models.ProjectItem
import com.example.antaresapp.domain.repository.ProjectsRepository

class GetProjectListUseCase(private val projectsRepository: ProjectsRepository) {
    fun execute() : List<ProjectItem>{
        return projectsRepository.getProjects()
    }
}