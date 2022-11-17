package com.example.antaresapp.domain.repository

import com.example.antaresapp.domain.models.SubTaskModel

interface SubTaskRepository {
    fun add(subTaskModel: SubTaskModel)
    fun remove(subTaskModel: SubTaskModel)
    fun get():List<SubTaskModel>
    fun clear()
}