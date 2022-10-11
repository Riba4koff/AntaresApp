package com.example.antaresapp.presentation.viewModels.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.antaresapp.data.RepositoryImpl.ProjectsRepositoryImpl
import com.example.antaresapp.data.storage.ProjectStorage.ProjectStorageImpl
import com.example.antaresapp.domain.models.ProjectItem
import com.example.antaresapp.domain.useCase.ProjectsUseCase.GetProjectListUseCase
import com.example.antaresapp.domain.useCase.ProjectsUseCase.SaveProjectItemUseCase

class ProjectViewModel(
    private val getProjectDataListUseCase : GetProjectListUseCase,
    private val saveProjectItemUseCase : SaveProjectItemUseCase,
) : ViewModel() {
    private val projectList = getProjectDataListUseCase.execute()

    private lateinit var projectItem: ProjectItem

    private val _dataProjectList : MutableLiveData<List<ProjectItem>> = MutableLiveData(projectList)
    val dataProjectList : LiveData<List<ProjectItem>> get() = _dataProjectList
    fun addProject(project : ProjectItem){ saveProjectItemUseCase.execute(project) }

    fun setProjectItem(_projectItem: ProjectItem){
        projectItem = _projectItem
    }
    fun getProjectItem() = projectItem

    companion object{
        val Factory : ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            private val projectStorage = ProjectStorageImpl()

            private val projectRepository = ProjectsRepositoryImpl(projectStorage)

            private val getProjectListUseCase = GetProjectListUseCase(projectRepository)

            private val saveProjectItemUseCase = SaveProjectItemUseCase(projectRepository)

            override fun <T : ViewModel> create(modelClass: Class<T>): T {

                return ProjectViewModel(
                    getProjectDataListUseCase = getProjectListUseCase,
                    saveProjectItemUseCase = saveProjectItemUseCase
                ) as T
            }
        }
    }

}