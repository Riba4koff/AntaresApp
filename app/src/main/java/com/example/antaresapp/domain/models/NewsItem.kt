package com.example.antaresapp.domain.models

import androidx.compose.ui.graphics.painter.Painter

data class NewsItem(
    val name: String? = "name",
    val surname: String? = "surname",
    val description: String? = "description",
    val date: String? = "dd.mm.yyyy",
    val painter : Painter ?= null
)
