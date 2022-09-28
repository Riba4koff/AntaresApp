package com.example.antaresapp.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun atTime(): String {
    return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
}
