package com.example.antaresapp.domain.repository

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.antaresapp.domain.models.TaskModel

interface TasksRepository {
    fun addTask(taskModel: TaskModel)
    fun removeTask(taskModel: TaskModel)
    fun get() : List<TaskModel>
}