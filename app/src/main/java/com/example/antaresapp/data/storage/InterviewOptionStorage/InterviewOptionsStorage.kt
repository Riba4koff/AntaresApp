package com.example.antaresapp.data.storage.InterviewOptionStorage

interface InterviewOptionsStorage {
    fun saveOption(option : String)
    fun getListOptions() : MutableList<String>
    fun deleteOption(index : Int)
}