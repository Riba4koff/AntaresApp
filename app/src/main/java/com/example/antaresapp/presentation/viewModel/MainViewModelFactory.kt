package com.example.antaresapp.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.antaresapp.data.RepositoryImpl.InterviewOptionsRepositoryImpl
import com.example.antaresapp.data.RepositoryImpl.InterviewRepositoryImpl
import com.example.antaresapp.data.RepositoryImpl.NewsRepositoryImpl
import com.example.antaresapp.data.RepositoryImpl.ProjectsRepositoryImpl
import com.example.antaresapp.data.storage.InterviewOptionStorage.InterviewOptionsStorageImpl
import com.example.antaresapp.data.storage.InterviewStorage.InterviewStorage
import com.example.antaresapp.data.storage.InterviewStorage.InterviewStorageImpl
import com.example.antaresapp.data.storage.NewsStorage.NewsStorageImpl
import com.example.antaresapp.data.storage.ProjectStorage.ProjectStorageImpl
import com.example.antaresapp.domain.repository.InterviewRepository
import com.example.antaresapp.domain.useCase.InterviewOptionsUseCase.DeleteOptionUseCase
import com.example.antaresapp.domain.useCase.InterviewOptionsUseCase.GetListInterviewOptionsUseCase
import com.example.antaresapp.domain.useCase.InterviewOptionsUseCase.SaveInterviewOptionUseCase
import com.example.antaresapp.domain.useCase.NewsUseCase.GetNewsFeedListUseCase
import com.example.antaresapp.domain.useCase.NewsUseCase.SaveItemNewsFeedUseCase
import com.example.antaresapp.domain.useCase.ProjectsUseCase.GetListInterviewInfoUseCase
import com.example.antaresapp.domain.useCase.ProjectsUseCase.GetProjectListUseCase
import com.example.antaresapp.domain.useCase.ProjectsUseCase.SaveInterviewUseCase
import com.example.antaresapp.domain.useCase.ProjectsUseCase.SaveProjectItemUseCase
import com.example.antaresapp.presentation.viewModelimport.MainViewModel

class MainViewModelFactory : ViewModelProvider.Factory{

    //Storages
    private val newsStorage = NewsStorageImpl()
    private val optionsStorage = InterviewOptionsStorageImpl()
    private val projectStorage = ProjectStorageImpl()
    private val interviewStorage = InterviewStorageImpl()

    //Repositories
    private val newsRepository = NewsRepositoryImpl(newsStorage)
    private val optionsRepository = InterviewOptionsRepositoryImpl(optionsStorage)
    private val projectsRepository = ProjectsRepositoryImpl(projectStorage)
    private val interviewRepository = InterviewRepositoryImpl(interviewStorage)

    //Use Cases
    private val getNewsFeedListUseCase = GetNewsFeedListUseCase(newsRepository)
    private val saveItemNewsFeedUseCase = SaveItemNewsFeedUseCase(newsRepository)

    private val getOptionsUseCase = GetListInterviewOptionsUseCase(optionsRepository)
    private val saveOptionUseCase = SaveInterviewOptionUseCase(optionsRepository)
    private val deleteOptionUseCase = DeleteOptionUseCase(optionsRepository)

    private val getProjectDataListUseCase = GetProjectListUseCase(projectsRepository)
    private val saveProjectItemUseCase = SaveProjectItemUseCase(projectsRepository)

    private val getListInterviewInfoUseCase = GetListInterviewInfoUseCase(interviewRepository)
    private val saveInterviewInfoUseCase = SaveInterviewUseCase(interviewRepository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return MainViewModel(
            getNewsFeedListUseCase = getNewsFeedListUseCase,
            saveItemNewsFeedUseCase = saveItemNewsFeedUseCase,
            getOptionsUseCase = getOptionsUseCase,
            saveOptionUseCase = saveOptionUseCase,
            deleteOptionUseCase = deleteOptionUseCase,
            getProjectDataListUseCase = getProjectDataListUseCase,
            saveProjectItemUseCase = saveProjectItemUseCase,
            getListInterviewInfoUseCase = getListInterviewInfoUseCase,
            saveInterviewInfoUseCase = saveInterviewInfoUseCase
        ) as T
    }

}