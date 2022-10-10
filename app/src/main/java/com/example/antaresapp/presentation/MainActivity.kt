package com.example.antaresapp.presentation

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.example.antaresapp.presentation.viewModels.viewModels.ProjectViewModel
import com.example.antaresapp.presentation.viewModels.viewModels.ScreenModelsViewModel
import com.example.antaresproject.StartApp

class MainActivity : ComponentActivity() {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Вью модель с новостями и опросами
        val screenModelsViewModel: ScreenModelsViewModel by viewModels { ScreenModelsViewModel.Factory }
        //Вью модель проектов
        val projectViewModel: ProjectViewModel by viewModels { ProjectViewModel.Factory }

        setContent {
           StartApp(projectViewModel = projectViewModel,screenModelsViewModel = screenModelsViewModel)
           /*InfoOfProjectScreen(navController = rememberNavController(),
                projectItem = ProjectItem(),
                projectViewModel = projectViewModel, rememberScaffoldState())*/

        }

    }
}
