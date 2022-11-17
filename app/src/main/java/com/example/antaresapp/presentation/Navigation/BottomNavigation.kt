package com.example.antaresapp.presentation.Navigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.antaresapp.R
import com.example.antaresapp.domain.*

//Навигация нижнего меню
@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(BottomNavItem.Calendar, BottomNavItem.News, BottomNavItem.Profile)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    androidx.compose.material.BottomNavigation(backgroundColor = colorResource(id = R.color.myColor),
        modifier = Modifier.height(60.dp)) {
        items.forEach { item ->
            BottomNavigationItem(icon = {
                Icon(painterResource(id = item.icon),
                    contentDescription = item.title,
                    modifier = Modifier.size(32.dp))
            },
                label = {
                    Text(text = item.title, fontSize = 9.sp)
                },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.4f),
                alwaysShowLabel = false,
                selected = currentRoute == item.screen_route,
                onClick = {
                    navController.navigate(item.screen_route) {

                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = false
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
        }
    }
}
