package com.example.antaresapp.domain.useCase.FinanceUseCase

import com.example.antaresapp.domain.repository.FinanceRepository

class GetListFinanceOperationsUseCase(private val financeRepository: FinanceRepository) {
    fun execute() = financeRepository.get()
}