package com.example.antaresapp.domain.models

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList

data class ProjectItem(
    var nameProject: String? = "",
    var description: String? = "",
    var dateProject: String? = "",
    var countPerson: String? = "",
    var intention: String? = "",
    var listTask : SnapshotStateList<TaskModel> ?= mutableStateListOf()
)
