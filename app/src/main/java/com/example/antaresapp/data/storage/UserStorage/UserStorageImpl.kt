package com.example.antaresapp.data.storage.UserStorage

import com.example.antaresapp.domain.models.UserInfo

class UserStorageImpl() : UserStorage {
    private val listUsers : MutableList<UserInfo> = mutableListOf()

    override fun saveUser(userInfo:UserInfo) {
        listUsers.add(userInfo)
    }

    override fun getListUser() = listUsers


}