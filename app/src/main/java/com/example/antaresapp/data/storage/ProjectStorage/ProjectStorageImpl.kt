package com.example.antaresapp.data.storage.ProjectStorage

import com.example.antaresapp.domain.models.ProjectItem

class ProjectStorageImpl : ProjectStorage {


    private val projectDataMutableList : MutableList<ProjectItem> = mutableListOf()

    override fun insertProject(projectItem: ProjectItem) {
        projectDataMutableList.add(projectItem)
    }

    override fun getProjects(): List<ProjectItem> = projectDataMutableList

}