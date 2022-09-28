package com.example.antaresapp.data.storage.InterviewStorage

import com.example.antaresapp.domain.models.InterviewInfo
interface InterviewStorage {
    fun saveInterview(interviewInfo: InterviewInfo)
    fun getInterviewList() : List<InterviewInfo>
}