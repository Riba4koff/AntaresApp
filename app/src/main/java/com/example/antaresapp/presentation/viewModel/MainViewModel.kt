package com.example.antaresapp.presentation.viewModelimport

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.antaresapp.domain.models.NewsItem
import com.example.antaresapp.domain.models.ProjectItem
import com.example.antaresapp.domain.useCase.InterviewOptionsUseCase.DeleteOptionUseCase
import com.example.antaresapp.domain.useCase.InterviewOptionsUseCase.GetListInterviewOptionsUseCase
import com.example.antaresapp.domain.useCase.InterviewOptionsUseCase.SaveInterviewOptionUseCase
import com.example.antaresapp.domain.useCase.NewsUseCase.GetNewsFeedListUseCase
import com.example.antaresapp.domain.useCase.NewsUseCase.SaveItemNewsFeedUseCase
import com.example.antaresapp.domain.useCase.ProjectsUseCase.GetListInterviewInfoUseCase
import com.example.antaresapp.domain.useCase.ProjectsUseCase.GetProjectListUseCase
import com.example.antaresapp.domain.useCase.ProjectsUseCase.SaveInterviewUseCase
import com.example.antaresapp.domain.useCase.ProjectsUseCase.SaveProjectItemUseCase

class MainViewModel(
    private val getNewsFeedListUseCase : GetNewsFeedListUseCase,
    private val saveItemNewsFeedUseCase: SaveItemNewsFeedUseCase,
    private val getOptionsUseCase : GetListInterviewOptionsUseCase,
    private val saveOptionUseCase : SaveInterviewOptionUseCase,
    private val deleteOptionUseCase : DeleteOptionUseCase,
    private val getProjectDataListUseCase : GetProjectListUseCase,
    private val saveProjectItemUseCase : SaveProjectItemUseCase,
    private val getListInterviewInfoUseCase : GetListInterviewInfoUseCase,
    private val saveInterviewInfoUseCase : SaveInterviewUseCase,

) : ViewModel() {

    ////////////////////////////////////// Interview ///////////////////////////////////////////////

    private var count_votes = mutableStateOf(0)

    private val optionList = getOptionsUseCase.execute()

    private val _dataOptionList: MutableLiveData<MutableList<String>> = MutableLiveData(optionList)

    val dataOptionList: LiveData<MutableList<String>> get() = _dataOptionList

    fun addOption(option : String){
        saveOptionUseCase.execute(option)
        _dataOptionList.postValue(_dataOptionList.value)
    }
    fun deleteOption(index: Int){
        deleteOptionUseCase.execute(index)
        _dataOptionList.postValue(_dataOptionList.value)
    }

    fun setDataInOptionList(){
        val result = 100 / count_votes.value
        val list = dataOptionList.value
        for (i in 0..optionList.size - 1){
            list?.set(i, optionList[i] + " $result%")
        }
        _dataOptionList.postValue(_dataOptionList.value)
    }

    fun getCountVotes() = count_votes.value
    fun addCountVotes(){ count_votes.value++ }


    ////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////// News //////////////////////////////////////////////////
    private val newsList = getNewsFeedListUseCase.execute()

    private val _dataNewsList : MutableLiveData<List<NewsItem>> = MutableLiveData(newsList)

    val dataNewsList : LiveData<List<NewsItem>> get() = _dataNewsList

    fun addNews(item : NewsItem){
        saveItemNewsFeedUseCase.execute(item)
    }

    //fun deleteNews()...

    ////////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////// Projects /////////////////////////////////////////////////

    private val projectList = getProjectDataListUseCase.execute()

    private val _dataProjectList : MutableLiveData<List<ProjectItem>> = MutableLiveData(projectList)

    val dataProjectList : LiveData<List<ProjectItem>> get() = _dataProjectList

    fun addProject(project : ProjectItem){
        saveProjectItemUseCase.execute(project)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

}