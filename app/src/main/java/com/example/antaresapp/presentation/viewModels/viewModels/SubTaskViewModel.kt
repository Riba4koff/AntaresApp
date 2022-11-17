package com.example.antaresapp.presentation.viewModels.viewModels

import android.text.Editable.Factory
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.antaresapp.data.RepositoryImpl.SubTasksRepositoryImpl
import com.example.antaresapp.domain.models.SubTaskModel
import com.example.antaresapp.domain.repository.SubTaskRepository

class SubTaskViewModel(
    private val subTaskRepository: SubTaskRepository
) : ViewModel() {
    private val subTasksList = subTaskRepository.get()

    private val _dataSubTaskList : MutableLiveData<List<SubTaskModel>> = MutableLiveData(subTasksList)

    val dataSubTaskList : LiveData<List<SubTaskModel>> get() = _dataSubTaskList

    fun addSubTask(subTaskModel: SubTaskModel){
        subTaskRepository.add(subTaskModel)
        _dataSubTaskList.postValue(_dataSubTaskList.value)
    }

    fun deleteSubTask(subTaskModel: SubTaskModel){
        subTaskRepository.remove(subTaskModel)
        _dataSubTaskList.postValue(_dataSubTaskList.value)
    }

    fun clear(){
        subTaskRepository.clear()
    }

    companion object {
        val Factory = object : ViewModelProvider.Factory {
            private val subTaskRepository = SubTasksRepositoryImpl()

            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return SubTaskViewModel(
                    subTaskRepository = subTaskRepository
                ) as T
            }
        }
    }
}