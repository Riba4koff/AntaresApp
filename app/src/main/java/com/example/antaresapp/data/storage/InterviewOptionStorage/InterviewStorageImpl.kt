package com.example.antaresapp.data.storage.InterviewOptionStorage

class InterviewOptionsStorageImpl : InterviewOptionsStorage {

    private var optionsList : MutableList<String> = mutableListOf()

    override fun saveOption(option: String) {
        optionsList.add(option)
    }

    override fun getListOptions(): MutableList<String> = optionsList

    override fun deleteOption(index: Int) {
        optionsList.removeAt(index)
    }
}