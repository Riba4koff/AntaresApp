package com.example.antaresproject

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.antaresapp.R
import com.example.antaresapp.domain.BottomNavItem
import com.example.antaresapp.domain.Login
import com.example.antaresapp.domain.MenuNavigationItems
import com.example.antaresapp.domain.models.UserInfo
import com.example.antaresapp.domain.models.UserRights
import com.example.antaresapp.presentation.Navigation.BottomNavigation
import com.example.antaresapp.presentation.Navigation.DrawerNavigation
import com.example.antaresapp.presentation.Navigation.NavigationGraph
import com.example.antaresapp.presentation.viewModels.viewModels.ProjectViewModel
import com.example.antaresapp.presentation.viewModels.viewModels.ScreenModelsViewModel
import com.example.antaresapp.ui.theme.myColor
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun StartApp(
    projectViewModel: ProjectViewModel,
    screenModelsViewModel: ScreenModelsViewModel
) {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val openDialog = remember { mutableStateOf(false) }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                onNavigationIconClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }, currentRoute = navBackStackEntry?.destination?.route
            )
        },
        drawerContent = {
            DrawerNavigation(
                navController = navController,
                scope = scope,
                closeDrawer = {
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                },
                openDialog = {
                    openDialog.value = true
                },
                currentRoute = navBackStackEntry?.destination?.route,
                scaffoldState = scaffoldState
            )
        },
        bottomBar = { BottomNavigation(navController = navController) }
    ) { paddingValues ->
        NavigationGraph(
            navController = navController,
            modifier = Modifier.padding(paddingValues = paddingValues),
            scaffoldState = scaffoldState,
            projectViewModel = projectViewModel,
            screenModelsViewModel = screenModelsViewModel,
            userInfo = UserInfo(rights = UserRights.Admin.right),
            openDialog = openDialog
        )
    }
}