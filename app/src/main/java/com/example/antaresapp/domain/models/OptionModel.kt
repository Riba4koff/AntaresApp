package com.example.antaresapp.domain.models

data class OptionModel(
    val id: Int,
    var option : String,
    var votes : Int,
    var percent : Float ?= 0f,
    var selected : Boolean ?= false
)