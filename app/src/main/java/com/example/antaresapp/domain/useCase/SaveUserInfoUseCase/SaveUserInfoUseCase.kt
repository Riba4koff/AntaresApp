package com.example.antaresapp.domain.useCase.ProjectsUseCase

import com.example.antaresapp.domain.models.UserInfo
import com.example.antaresapp.domain.repository.UserRepository

class SaveUserInfoUseCase(private val userRepository: UserRepository) {
    fun execute(userInfo: UserInfo){
        userRepository.save(userInfo)
    }

}