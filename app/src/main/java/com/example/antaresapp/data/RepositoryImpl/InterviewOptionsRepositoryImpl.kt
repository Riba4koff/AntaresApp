package com.example.antaresapp.data.RepositoryImpl

import com.example.antaresapp.data.storage.InterviewOptionStorage.InterviewOptionsStorage
import com.example.antaresapp.domain.repository.InterviewOptionsRepository

class InterviewOptionsRepositoryImpl(private val interviewOptionsStorage: InterviewOptionsStorage) : InterviewOptionsRepository {
    override fun save(option: String) {
        interviewOptionsStorage.saveOption(option)
    }
    override fun getList(): MutableList<String> = interviewOptionsStorage.getListOptions()

    override fun delete(index: Int) {
        interviewOptionsStorage.deleteOption(index)
    }

}