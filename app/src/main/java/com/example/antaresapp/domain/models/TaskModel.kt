package com.example.antaresapp.domain.models

import androidx.compose.ui.state.ToggleableState

data class TaskModel(
    var id: Int ?= 0,
    var nameTask: String? = "Задача",
    var state: Boolean,
)
