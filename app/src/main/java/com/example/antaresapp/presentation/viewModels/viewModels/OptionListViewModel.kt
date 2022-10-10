package com.example.antaresapp.presentation.viewModels.viewModels

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.antaresapp.data.RepositoryImpl.SurveyOptionsRepositoryImpl
import com.example.antaresapp.data.storage.SurveyOptionStorage.SurveyOptionsStorageImpl
import com.example.antaresapp.domain.models.OptionModel
import com.example.antaresapp.domain.useCase.SurveyOptionsUseCase.DeleteOptionUseCase
import com.example.antaresapp.domain.useCase.SurveyOptionsUseCase.GetListSurveyOptionsUseCase
import com.example.antaresapp.domain.useCase.SurveyOptionsUseCase.SaveSurveyOptionUseCase


class OptionListViewModel(
    private val getOptionsUseCase : GetListSurveyOptionsUseCase,
    private val saveOptionUseCase : SaveSurveyOptionUseCase,
    private val deleteOptionUseCase : DeleteOptionUseCase,
    ) : ViewModel() {
    ////////////////////////////////////// Survey Options - Варианты опроса ////////////////////////

    private var optionList = getOptionsUseCase.execute()

    private val _dataOptionList : MutableLiveData<SnapshotStateList<OptionModel>> = MutableLiveData(optionList)

    val dataOptionList: LiveData<SnapshotStateList<OptionModel>> get() = _dataOptionList

    fun addOption(index: Int, option : OptionModel) : List<OptionModel>{
        saveOptionUseCase.execute(index, option)
        _dataOptionList.postValue(_dataOptionList.value)
        return optionList
    }
    fun deleteOption(index : Int){
        deleteOptionUseCase.execute(index)
    }
    fun clear(){
        while(optionList.isNotEmpty()){
            optionList.removeAt(0)
        }
    }

    companion object{
        val Factory : ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            //Storages
            private val optionsStorage = SurveyOptionsStorageImpl()
            //Repositories
            private val optionsRepository = SurveyOptionsRepositoryImpl(optionsStorage)

            private val getOptionsUseCase = GetListSurveyOptionsUseCase(optionsRepository)
            private val saveOptionUseCase = SaveSurveyOptionUseCase(optionsRepository)
            private val deleteOptionUseCase = DeleteOptionUseCase(optionsRepository)

            override fun <T : ViewModel> create(modelClass: Class<T>): T {

                return OptionListViewModel(
                    getOptionsUseCase = getOptionsUseCase,
                    saveOptionUseCase = saveOptionUseCase,
                    deleteOptionUseCase = deleteOptionUseCase,
                ) as T
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

}