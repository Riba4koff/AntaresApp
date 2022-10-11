package com.example.antaresapp.presentation.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.antaresapp.presentation.Navigation.DrawerNavigation
import com.example.antaresapp.ui.theme.myColor
import kotlinx.coroutines.launch

@Composable
fun CalendarScreen(scaffoldState: ScaffoldState, navController: NavController){
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text("Календарь")
                },
                backgroundColor = myColor,
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "menu")
                    }
                }
            )
        }, drawerContent = {
            DrawerNavigation(navController = navController, scope = scope, closeDrawer = {
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            })
        },
        bottomBar = {
            com.example.antaresapp.presentation.Navigation.BottomNavigation(navController = navController)
        }
    ) {
        BodyCalendarScreen(modifier = Modifier.padding(paddingValues = it))
    }
}

@Composable
fun BodyCalendarScreen(modifier : Modifier){

}