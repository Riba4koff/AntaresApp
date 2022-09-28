package com.example.antaresapp.domain.useCase.ProjectsUseCase

import com.example.antaresapp.domain.repository.UserRepository

class GetListUsersUseCase(private val userRepository: UserRepository) {
    fun execute() = userRepository.get()
}