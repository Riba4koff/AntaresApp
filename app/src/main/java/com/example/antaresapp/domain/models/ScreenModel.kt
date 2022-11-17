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
        val listOptionItem: List<OptionModel>,
        var showRadioBoolean: Boolean ?= true,
        var refreshCountVotes : Int ?= 0,
        var allow_click : Boolean ?= true,
        var lastChosenElement : Int ?= -1
    ) : ScreenModel()

    data class NewsItem(
        val name: String? = "name",
        val surname: String? = "surname",
        val description: String? = "description",
        val date: String? = "dd.mm.yyyy",
        val painter: Painter? = null
    ) : ScreenModel()
}
