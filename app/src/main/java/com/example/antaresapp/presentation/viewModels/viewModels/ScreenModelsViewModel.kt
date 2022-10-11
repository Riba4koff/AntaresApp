package com.example.antaresapp.presentation.viewModels.viewModels

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.antaresapp.data.RepositoryImpl.ScreenModelRepositoryImpl
import com.example.antaresapp.data.storage.ScreenModelStorage.ScreenModelStorage
import com.example.antaresapp.domain.models.OptionModel
import com.example.antaresapp.domain.models.ScreenModel
import com.example.antaresapp.domain.repository.ScreenModelRepository
import com.example.antaresapp.domain.useCase.ProjectsUseCase.SaveSurveyUseCase

class ScreenModelsViewModel(
    private val saveSurveyInfoUseCase : SaveSurveyUseCase,
    private val screenModelRepository : ScreenModelRepository
) : ViewModel() {

    /////////////////////////////////     Карточки новостей и опросов     //////////////////////////
    private var percent = 0f

    private val screenModelList = screenModelRepository.get()

    private val _dataScreenModelList : MutableLiveData<SnapshotStateList<ScreenModel>> = MutableLiveData(screenModelList)

    val dataScreenModelList : LiveData<SnapshotStateList<ScreenModel>> get() = _dataScreenModelList

    fun saveNews(newsItem: ScreenModel.NewsItem){
        saveSurveyInfoUseCase.execute(newsItem)
        _dataScreenModelList.postValue(_dataScreenModelList.value)
    }

    fun deleteNews(newsItem: ScreenModel.NewsItem){
        screenModelRepository.deleteNews(newsItem)
        _dataScreenModelList.postValue(_dataScreenModelList.value)
    }

    fun saveSurvey(survey: ScreenModel.SurveyInfo){
        screenModelRepository.saveSurvey(survey)
        _dataScreenModelList.postValue(_dataScreenModelList.value)
    }

    fun deleteSurvey(survey: ScreenModel.SurveyInfo){
        screenModelRepository.deleteSurvey(survey)
        _dataScreenModelList.postValue(_dataScreenModelList.value)
    }

    fun addVotes(index : Int, survey: ScreenModel.SurveyInfo){
        survey.votes++
        survey.listOptionItem[index].votes++
        _dataScreenModelList.postValue(_dataScreenModelList.value)
    }

    fun deleteVotes(index : Int, survey: ScreenModel.SurveyInfo){
        survey.votes--
        survey.listOptionItem[index].votes--
    }

    fun findPercent(optionList: List<OptionModel>, survey: ScreenModel.SurveyInfo){
        for (i in 0..optionList.size - 1){
            if (optionList[i].votes == 0){
                optionList[i].percent = 0f
            }
            else {
                percent = ((optionList[i].votes / survey.votes) * 100).toFloat()
                optionList[i].percent = percent
            }
        }
    }

    fun getIndex(optionModel: OptionModel, listOptionModel: List<OptionModel>) : Int{
        listOptionModel.forEach{
            if (it == optionModel) return listOptionModel.indexOf(optionModel)
        }
        return 0
    }

    fun getPercent(optionList: List<OptionModel>, index: Int) : Float{
        return optionList[index].percent!!.toFloat()
    }

    companion object{
        val Factory : ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            private val screenModelStorage = ScreenModelStorage()

            private val screenModelRepository = ScreenModelRepositoryImpl(screenModelStorage)

            private val saveSurveyInfoUseCase = SaveSurveyUseCase(screenModelRepository)

            override fun <T : ViewModel> create(modelClass: Class<T>): T {

                return ScreenModelsViewModel(
                    saveSurveyInfoUseCase = saveSurveyInfoUseCase,
                    screenModelRepository = screenModelRepository
                ) as T
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
}