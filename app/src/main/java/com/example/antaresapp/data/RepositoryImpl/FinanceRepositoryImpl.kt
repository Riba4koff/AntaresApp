package com.example.antaresapp.data.RepositoryImpl

import com.example.antaresapp.data.storage.FinanceStorage.FinanceStorage
import com.example.antaresapp.domain.models.FinanceModel
import com.example.antaresapp.domain.repository.FinanceRepository

class FinanceRepositoryImpl(private val financeStorage: FinanceStorage) : FinanceRepository {
    override fun save(financeModel: FinanceModel) {
        financeStorage.save(financeModel)
    }

    override fun get(): List<FinanceModel> = financeStorage.get()
}