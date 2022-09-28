package com.example.antaresapp.domain.repository

interface InterviewOptionsRepository {
    fun save(option : String)
    fun getList() : MutableList<String>
    fun delete(index : Int)
}