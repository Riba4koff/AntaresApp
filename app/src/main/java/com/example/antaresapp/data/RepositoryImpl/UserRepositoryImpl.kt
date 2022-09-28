package com.example.antaresapp.data.RepositoryImpl

import com.example.antaresapp.data.storage.UserStorage.UserStorage
import com.example.antaresapp.domain.models.UserInfo
import com.example.antaresapp.domain.repository.UserRepository

class UserRepositoryImpl(private val userStorage: UserStorage) : UserRepository {
    override fun save(userInfo: UserInfo) {
        userStorage.saveUser(userInfo)
    }

    override fun get() = userStorage.getListUser()

}