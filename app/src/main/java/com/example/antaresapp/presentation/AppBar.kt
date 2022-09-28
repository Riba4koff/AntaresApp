package com.example.antaresproject

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import com.example.antaresapp.presentation.Navigation.*
import com.example.antaresapp.ui.theme.myColor

@Composable
fun AppBar(
    onNavigationIconClick: () -> Unit,
    currentRoute: String?
) {
    if (currentRoute == AddItemNews.AddNews.screen_route){}
    else if (currentRoute == ProjectItems.InfoOfProject.screen_route)
    else {
        TopAppBar(
            title = {
                val title = when (currentRoute) {
                    MenuNavigationItems.Projects.screen_route -> "Проекты"
                    BottomNavItem.Profile.screen_route -> "Профиль"
                    BottomNavItem.Calendar.screen_route -> "Календарь"
                    MenuNavigationItems.Balance.screen_route -> "Финансы"
                    BottomNavItem.News.screen_route -> "Новости"
                    MenuNavigationItems.Settings.screen_route -> "Настройки"
                    MenuNavigationItems.FeedBack.screen_route -> "Обратная связь"
                    ProjectItems.InfoOfProject.screen_route -> "Информация о проекте"
                    InterView.Interview.screen_route -> "Создать опрос"
                    InterView.AddOptionInterview.screen_route -> "Добавить вариант"
                    else -> "Antares App"
                }
                Text(text = title)
            },
            backgroundColor = myColor,
            navigationIcon = {
                IconButton(onClick = onNavigationIconClick) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = "menu")
                }
            }
        )
    }
}