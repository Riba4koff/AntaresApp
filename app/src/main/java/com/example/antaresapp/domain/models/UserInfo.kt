package com.example.antaresapp.domain.models

data class UserInfo(
    val name: String ?= "Name",
    val surname: String ?= "Surname",
    var age: String ?= "",
    var telegram: String ?= "",
    var competition: String ?= "",
    var _mail: String ?= "",
    var project: String ?= ""
)