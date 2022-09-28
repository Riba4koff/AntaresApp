package com.example.antaresapp.domain.repository

import com.example.antaresapp.domain.models.UserInfo

interface UserRepository {
    fun save(userInfo: UserInfo)
    fun get() : List<UserInfo>
}