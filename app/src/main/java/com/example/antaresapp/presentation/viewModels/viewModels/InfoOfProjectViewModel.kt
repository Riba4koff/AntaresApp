package com.example.antaresapp.presentation.viewModels.viewModels

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.antaresapp.domain.models.ProjectItem
import com.example.antaresapp.domain.models.TaskModelNeTrogat

class InfoOfProjectViewModel(
    project: ProjectItem
) : ViewModel() {

    private val listTasks = project.listTask

    private val _dataListTasks : MutableLiveData<SnapshotStateList<TaskModelNeTrogat>> = MutableLiveData(listTasks)

    val dataListTasks : LiveData<SnapshotStateList<TaskModelNeTrogat>> get() = _dataListTasks

    fun addTask(project: ProjectItem, item : TaskModelNeTrogat, index: Int){
        project.listTask?.add(index, item)
        _dataListTasks.postValue(_dataListTasks.value)
    }
    fun deleteTask(project: ProjectItem, index : Int){
        project.listTask?.removeAt(index)
        _dataListTasks.postValue(_dataListTasks.value)
    }
}