package com.example.antaresapp.domain.repository

import com.example.antaresapp.domain.models.FinanceModel

interface FinanceRepository {
    fun save(financeModel: FinanceModel)
    fun get() : List<FinanceModel>
}