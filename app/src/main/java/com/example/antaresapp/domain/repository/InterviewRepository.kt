package com.example.antaresapp.domain.repository

import com.example.antaresapp.domain.models.InterviewInfo


interface InterviewRepository {
    fun save(interviewInfo: InterviewInfo)
    fun get() : List<InterviewInfo>
}