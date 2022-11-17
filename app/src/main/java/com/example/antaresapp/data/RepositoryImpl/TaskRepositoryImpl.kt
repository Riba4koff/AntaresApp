package com.example.antaresapp.data.RepositoryImpl

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.antaresapp.domain.models.TaskModel
import com.example.antaresapp.domain.repository.TasksRepository

class TaskRepositoryImpl : TasksRepository {
    private val listTasksModel : SnapshotStateList<TaskModel> = mutableStateListOf()

    override fun addTask(taskModel: TaskModel) {
        listTasksModel.add(taskModel)
    }

    override fun removeTask(taskModel: TaskModel) {
        listTasksModel.remove(taskModel)
    }

    override fun get(): List<TaskModel> = listTasksModel

}