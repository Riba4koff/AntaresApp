package com.example.antaresapp.presentation.viewModels.viewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.antaresapp.data.RepositoryImpl.ScreenModelRepositoryImpl
import com.example.antaresapp.data.RepositoryImpl.TaskRepositoryImpl
import com.example.antaresapp.data.storage.ScreenModelStorage.ScreenModelStorage
import com.example.antaresapp.domain.models.TaskModel
import com.example.antaresapp.domain.repository.TasksRepository
import com.example.antaresapp.domain.useCase.ProjectsUseCase.SaveSurveyUseCase

class TasksViewModel(
    private val tasksRepository: TasksRepository
) : ViewModel() {
    private val listTasksModel = tasksRepository.get()
    private lateinit var _taskModel: TaskModel

    private val _dataListTasksModel : MutableLiveData<List<TaskModel>> = MutableLiveData(listTasksModel)

    val dataListTaskModel : LiveData<List<TaskModel>> get() = _dataListTasksModel

    fun addTask(taskModel: TaskModel) {
        tasksRepository.addTask(taskModel)
        _dataListTasksModel.postValue(_dataListTasksModel.value)
    }
    fun deleteTask(taskModel: TaskModel){
        tasksRepository.removeTask(taskModel)
        _dataListTasksModel.postValue(_dataListTasksModel.value)
    }

    fun setTask(taskModel: TaskModel){
        _taskModel = taskModel
    }
    fun getTask() = _taskModel

    companion object{
        val Factory : ViewModelProvider.Factory = object : ViewModelProvider.Factory{

            private val tasksRepository : TasksRepository = TaskRepositoryImpl()

            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return TasksViewModel(
                    tasksRepository = tasksRepository
                ) as T
            }
        }
    }
}