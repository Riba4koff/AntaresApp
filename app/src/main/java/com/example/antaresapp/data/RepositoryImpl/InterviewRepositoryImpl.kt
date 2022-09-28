package com.example.antaresapp.data.RepositoryImpl

import com.example.antaresapp.data.storage.InterviewStorage.InterviewStorage
import com.example.antaresapp.domain.models.InterviewInfo
import com.example.antaresapp.domain.repository.InterviewRepository


class InterviewRepositoryImpl(private val interviewStorage: InterviewStorage) :
    InterviewRepository {
    override fun save(interviewInfo: InterviewInfo) {
        interviewStorage.saveInterview(interviewInfo)
    }

    override fun get(): List<InterviewInfo> = interviewStorage.getInterviewList()

}