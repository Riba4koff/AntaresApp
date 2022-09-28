package com.example.antaresapp.data.storage.FinanceStorage

import com.example.antaresapp.domain.models.FinanceModel


interface FinanceStorage {

    fun save(financeModel: FinanceModel)
    fun get() : List<FinanceModel>

}