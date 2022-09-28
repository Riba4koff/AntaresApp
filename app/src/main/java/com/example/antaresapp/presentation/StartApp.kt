package com.example.antaresproject

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.antaresapp.R
import com.example.antaresapp.presentation.Navigation.BottomNavigation
import com.example.antaresapp.presentation.Navigation.DataMenuItemList
import com.example.antaresapp.presentation.Navigation.DrawerNavigation
import com.example.antaresapp.presentation.Navigation.NavigationGraph
import com.example.antaresapp.presentation.viewModelimport.MainViewModel
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun StartApp(viewModel: MainViewModel) {
    //Навигационное меню
    val listOfMenuItem = listOf(
        DataMenuItemList(
            id = "profile",
            title = "Профиль",
            contentDescription = "Go to profile screen",
            icon = painterResource(id = R.drawable.person),
        ),
        DataMenuItemList(
            id = "projects",
            title = "Проекты",
            contentDescription = "Go to projects screen",
            icon = painterResource(id = R.drawable.projects)
        ),
        DataMenuItemList(
            id = "calendar",
            title = "Календарь",
            contentDescription = "Go to calendar screen",
            icon = painterResource(id = R.drawable.icon_calendar_bottom_navigation)
        ),
        DataMenuItemList(
            id = "balance",
            title = "Финансы",
            contentDescription = "Go to balance screen",
            icon = painterResource(id = R.drawable.balance)
        ),
        DataMenuItemList(
            id = "newsfeed",
            title = "Новостная лента",
            contentDescription = "Go to news feed screen",
            icon = painterResource(id = R.drawable.news)
        ),
        DataMenuItemList(
            id = "settings",
            title = "Настройки",
            contentDescription = "Go to settings screen",
            icon = painterResource(id = R.drawable.settings)
        ),
        DataMenuItemList(
            id = "feedback",
            title = "Обратная связь",
            contentDescription = "Go to feedback screen",
            icon = painterResource(id = R.drawable.feedback)
        )
    )
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    val navBackStackEntry by navController.currentBackStackEntryAsState()

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
                listOfDataMenuItem = listOfMenuItem,
                scope = scope,
                closeDrawer = {
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                }
            )
        },
        bottomBar = {BottomNavigation(navController = navController)}
    ) { paddingValues ->
        NavigationGraph(
            navController = navController,
            modifier = Modifier.padding(paddingValues = paddingValues),
            scaffoldState = scaffoldState,
            viewModel = viewModel
        )
    }
}