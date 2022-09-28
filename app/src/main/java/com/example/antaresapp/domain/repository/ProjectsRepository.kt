package com.example.antaresapp.domain.repository

import com.example.antaresapp.domain.models.ProjectItem

interface ProjectsRepository {
    fun insertProject(projectItem: ProjectItem)
    fun getProjects() : List<ProjectItem>
}