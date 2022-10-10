package com.example.antaresapp.data.storage.SurveyOptionStorage

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.antaresapp.domain.models.OptionModel

interface SurveyOptionsStorage {
    fun saveOption(index: Int ,option : OptionModel)
    fun getListOptions() : SnapshotStateList<OptionModel>
    fun deleteOption(index : Int)
}