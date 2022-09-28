package com.example.antaresapp.data.storage.InterviewStorage

import com.example.antaresapp.domain.models.InterviewInfo

class InterviewStorageImpl : InterviewStorage {

    private val listInterview : MutableList<InterviewInfo> = mutableListOf()

    override fun saveInterview(interviewInfo: InterviewInfo) {
        listInterview.add(interviewInfo)
    }

    override fun getInterviewList() = listInterview

}