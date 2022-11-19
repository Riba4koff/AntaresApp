package com.example.antaresapp.domain.models

data class TaskModel(
    val nameTask: String ?= "Task",
    val description: String ?= "Task Description",
    val creator: String ?= "nickname creator",
    val executor: String ?= "nickname executor",
    val timeAdding: String ?= "20.12.2020",
    val listSubTasks: List<SubTaskModel>,
    var progress : Float ?= 0f,
    var complete : Boolean ?= false
)


data class SubTaskModel(
    val nameSubTask : String,
    var state: Boolean
)