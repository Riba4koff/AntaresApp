package com.example.antaresproject

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.antaresapp.domain.Login
import com.example.antaresapp.domain.MenuNavigationItems
import com.example.antaresapp.domain.models.UserInfo
import com.example.antaresapp.domain.models.UserRights
import com.example.antaresapp.presentation.Navigation.BottomNavigation
import com.example.antaresapp.presentation.Navigation.DrawerNavigation
import com.example.antaresapp.presentation.Navigation.NavigationGraph
import com.example.antaresapp.presentation.viewModels.viewModels.*
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun StartApp(
    projectViewModel: ProjectViewModel,
    screenModelsViewModel: ScreenModelsViewModel,
    tasksViewModel: TasksViewModel,
    subTaskViewModel: SubTaskViewModel,
    optionListViewModel: OptionListViewModel
) {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val startDestination : String

    NavigationGraph(
        navController = navController,
        scaffoldState = scaffoldState,
        projectViewModel = projectViewModel,
        tasksViewModel = tasksViewModel,
        screenModelsViewModel = screenModelsViewModel,
        userInfo = UserInfo(rights = UserRights.Admin.right),
        subTaskViewModel = subTaskViewModel,
        optionListViewModel = optionListViewModel
    )
}