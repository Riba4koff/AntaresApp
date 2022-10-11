package com.example.antaresapp.domain.models

import androidx.compose.ui.graphics.painter.Painter

sealed class ScreenModel() {
    data class SurveyInfo(
        val nameSurvey: String? = "Survey",
        val description: String? = "description",
        var votes: Int,
        val name: String? = "name",
        val surname: String? = "surname",
        val date: String? = "dd.mm.yyyy",
        val listOptionItem: List<OptionModel>
    ) : ScreenModel()

    data class NewsItem(
        val name: String? = "name",
        val surname: String? = "surname",
        val description: String? = "description",
        val date: String? = "dd.mm.yyyy",
        val painter: Painter? = null
    ) : ScreenModel()
}
