package com.example.antaresapp.data.storage.SurveyOptionStorage

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.antaresapp.domain.models.OptionModel


class SurveyOptionsStorageImpl : SurveyOptionsStorage {
    private var optionsList = mutableStateListOf<OptionModel>()


    override fun saveOption(index : Int, option: OptionModel) {
        optionsList.add(index, option)
    }
    override fun getListOptions(): SnapshotStateList<OptionModel> = optionsList
    override fun deleteOption(index: Int) {
        optionsList.removeAt(index)
    }
}