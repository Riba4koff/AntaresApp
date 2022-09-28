package com.example.antaresapp.data.RepositoryImpl

import com.example.antaresapp.data.storage.ProjectStorage.ProjectStorage
import com.example.antaresapp.domain.models.ProjectItem
import com.example.antaresapp.domain.repository.ProjectsRepository


class ProjectsRepositoryImpl(private val projectStorage: ProjectStorage) : ProjectsRepository {
    override fun insertProject(projectItem: ProjectItem) {
        projectStorage.insertProject(projectItem)
    }
    override fun getProjects(): List<ProjectItem> = projectStorage.getProjects()

}