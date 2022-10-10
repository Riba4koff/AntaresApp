package com.example.antaresapp.domain.repository

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.antaresapp.domain.models.OptionModel

interface SurveyOptionsRepository {
    fun save(index : Int, option : OptionModel)
    fun getList() : SnapshotStateList<OptionModel>
    fun delete(index : Int)
}