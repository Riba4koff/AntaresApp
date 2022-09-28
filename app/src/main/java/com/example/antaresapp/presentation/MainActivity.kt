package com.example.antaresapp.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.antaresapp.CardInterview
import com.example.antaresapp.domain.models.InterviewInfo
import com.example.antaresapp.presentation.viewModel.MainViewModelFactory
import com.example.antaresapp.presentation.viewModelimport.MainViewModel
import com.example.antaresproject.StartApp

private lateinit var viewModel : MainViewModel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, MainViewModelFactory()).get(MainViewModel::class.java)

        setContent {
            //StartApp(viewModel = viewModel)
            CardInterview(interviewInfo = InterviewInfo(), viewModel = viewModel)
        }
    }
}