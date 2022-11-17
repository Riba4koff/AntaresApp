package com.example.antaresapp.domain.models

data class TaskModelNeTrogat(
    var id: Int ?= 0,
    var nameTask: String? = "Задача",
    var state: Boolean,
)
