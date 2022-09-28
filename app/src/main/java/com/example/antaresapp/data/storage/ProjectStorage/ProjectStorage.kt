package com.example.antaresapp.data.storage.ProjectStorage

import com.example.antaresapp.domain.models.ProjectItem

interface ProjectStorage {

    fun insertProject(projectItem:ProjectItem)
    fun getProjects(): List<ProjectItem>

}