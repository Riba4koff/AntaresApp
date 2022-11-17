package com.example.antaresapp.data.RepositoryImpl

import androidx.compose.runtime.mutableStateListOf
import com.example.antaresapp.domain.models.SubTaskModel
import com.example.antaresapp.domain.repository.SubTaskRepository

class SubTasksRepositoryImpl : SubTaskRepository {
    private val subTasksList = mutableStateListOf<SubTaskModel>()

    override fun add(subTaskModel: SubTaskModel) {
        subTasksList.add(0, subTaskModel)
    }

    override fun remove(subTaskModel: SubTaskModel) {
        subTasksList.remove(subTaskModel)
    }

    override fun get(): List<SubTaskModel> = subTasksList

    override fun clear() {
        while (!subTasksList.isEmpty()) subTasksList.removeAt(0)
    }
}