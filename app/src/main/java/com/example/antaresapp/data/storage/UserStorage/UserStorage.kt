package com.example.antaresapp.data.storage.UserStorage

import com.example.antaresapp.domain.models.UserInfo

interface UserStorage {
    fun saveUser(userInfo:UserInfo)
    fun getListUser() : List<UserInfo>
}