package com.example.antaresapp.domain.models

sealed class UserRights(val right : String){
    object Admin : UserRights("Admin")
    object Moderator : UserRights("Moderator")
    object User : UserRights("User")
}
