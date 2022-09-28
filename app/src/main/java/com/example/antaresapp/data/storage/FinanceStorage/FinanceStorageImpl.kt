package com.example.antaresapp.data.storage.FinanceStorage

import com.example.antaresapp.domain.models.FinanceModel

class FinanceStorageImpl : FinanceStorage {

    private val listFinanceModels = mutableListOf<FinanceModel>()

    override fun save(financeModel: FinanceModel){
        listFinanceModels.add(financeModel)
    }

    override fun get() = listFinanceModels

}