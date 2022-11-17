package com.example.antaresapp.presentation

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.example.antaresapp.presentation.viewModels.viewModels.*
import com.example.antaresproject.StartApp

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val screenModelsViewModel: ScreenModelsViewModel by viewModels { ScreenModelsViewModel.Factory }
        val projectViewModel: ProjectViewModel by viewModels { ProjectViewModel.Factory }
        val tasksViewModel: TasksViewModel by viewModels { TasksViewModel.Factory }
        val subTaskViewModel : SubTaskViewModel by viewModels { SubTaskViewModel.Factory }
        val optionListViewModel : OptionListViewModel by viewModels { OptionListViewModel.Factory }

        setContent {
            StartApp(projectViewModel = projectViewModel,
                screenModelsViewModel = screenModelsViewModel,
                tasksViewModel = tasksViewModel,
                subTaskViewModel = subTaskViewModel,
                optionListViewModel = optionListViewModel
            )
            /*InfoOfProjectScreen(navController = rememberNavController(),
                 projectItem = ProjectItem(),
                 projectViewModel = projectViewModel, rememberScaffoldState())*/

        }

    }
}
