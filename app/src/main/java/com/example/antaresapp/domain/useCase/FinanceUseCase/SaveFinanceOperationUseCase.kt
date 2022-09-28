package com.example.antaresapp.domain.useCase.ProjectsUseCase

import com.example.antaresapp.domain.models.FinanceModel
import com.example.antaresapp.domain.repository.FinanceRepository

class SaveFinanceOperationUseCase(private val financeRepository: FinanceRepository) {
    fun execute(financeModel: FinanceModel){
        financeRepository.save(financeModel)
    }
}