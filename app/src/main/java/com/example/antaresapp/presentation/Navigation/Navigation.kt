package com.example.antaresapp.presentation.Navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.antaresapp.*
import com.example.antaresapp.R
import com.example.antaresapp.domain.models.UserInfo
import com.example.antaresapp.presentation.view.FeedBackScreen
import com.example.antaresapp.presentation.view.InfoOfProjectScreen
import com.example.antaresapp.presentation.view.ProfileScreen
import com.example.antaresapp.presentation.viewModelimport.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


//Навигация левого меню
@Composable
fun DrawerNavigation(
    navController: NavHostController,
    listOfDataMenuItem: List<DataMenuItemList>,
    scope: CoroutineScope,
    closeDrawer: () -> Unit = {},
) {
    DrawerHeader()
    DrawerBody(
        itemListData = listOfDataMenuItem,
        onItemClick = { item ->
            scope.launch {
                when (item.id) {
                    "profile" -> navController.navigate(BottomNavItem.Profile.screen_route)
                    "projects" -> navController.navigate(MenuNavigationItems.Projects.screen_route)
                    "calendar" -> navController.navigate(BottomNavItem.Calendar.screen_route)
                    "balance" -> navController.navigate(MenuNavigationItems.Balance.screen_route)
                    "newsfeed" -> navController.navigate(BottomNavItem.News.screen_route)
                    "settings" -> navController.navigate(MenuNavigationItems.Settings.screen_route)
                    "feedback" -> navController.navigate(MenuNavigationItems.FeedBack.screen_route)
                }
                closeDrawer()
            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationGraph(
    modifier: Modifier,
    navController: NavHostController,
    scaffoldState: ScaffoldState,
    viewModel: MainViewModel,
) {
    NavHost(navController, startDestination = BottomNavItem.News.screen_route) {
        //Новости
        composable(BottomNavItem.News.screen_route) {
            NewsFeed(navController = navController, viewModel = viewModel)
        }
        //Календарь
        composable(BottomNavItem.Calendar.screen_route) {

        }
        //Профиль
        composable(BottomNavItem.Profile.screen_route) {
            ProfileScreen(modifier = modifier)
        }
        //Проекты
        composable(MenuNavigationItems.Projects.screen_route) {
            ShowListProject(navController = navController, modifier, viewModel)
        }
        //Финансы
        composable(MenuNavigationItems.Balance.screen_route) {
            FinanceScreen()
        }
        //Настройки
        composable(MenuNavigationItems.Settings.screen_route) {

        }
        //Обратная связь
        composable(MenuNavigationItems.FeedBack.screen_route) {
            FeedBackScreen()
        }
        //Добавить новость
        composable(AddItemNews.AddNews.screen_route) {
            InsertNewsScreen(navController,
                scaffoldState,
                userInfo = UserInfo(),
                viewModel = viewModel)
        }
        //Добавить проект
        composable(ProjectItems.AddProjectItem.screen_route) {
            InsertProjectItemScreen(
                modifier = modifier,
                navController = navController,
                scaffoldState = scaffoldState,
                viewModel = viewModel
            )
        }
        //Создать опрос
        composable(InterView.Interview.screen_route) {
            Interview(viewModel, navController)
        }
        //Добавить вариант опроса
        composable(InterView.AddOptionInterview.screen_route) {
            AddInterviewOption(navController = navController, viewModel = viewModel)
        }
        composable(ProjectItems.InfoOfProject.screen_route){
            InfoOfProjectScreen(modifier, navController)
        }
    }
}


//Навигация нижнего меню
@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        BottomNavItem.Calendar,
        BottomNavItem.News,
        BottomNavItem.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    if (currentRoute == AddItemNews.AddNews.screen_route)
    else if (currentRoute == ProjectItems.InfoOfProject.screen_route)
    else {
        BottomNavigation(
            backgroundColor = colorResource(id = R.color.myColor),
            modifier = Modifier
                .height(65.dp)
        ) {
            items.forEach { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painterResource(id = item.icon),
                            contentDescription = item.title,
                            modifier = Modifier.size(32.dp)
                        )
                    },
                    label = {
                        Text(
                            text = item.title,
                            fontSize = 9.sp
                        )
                    },
                    selectedContentColor = Color.Black,
                    unselectedContentColor = Color.Black.copy(0.4f),
                    alwaysShowLabel = true,
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
                    }
                )
            }
        }
    }
}


sealed class BottomNavItem(var title: String, var icon: Int, var screen_route: String) {
    object Calendar :
        BottomNavItem("Calendar", R.drawable.icon_calendar_bottom_navigation, "calendar")

    object Profile : BottomNavItem("Profile", R.drawable.icon_profile_bottom_navigatin, "profile")
    object News : BottomNavItem("News", R.drawable.icon_news_bottom_navigation, "news")
}

sealed class MenuNavigationItems(
    var title: String,
    var icon: Int,
    var screen_route: String,
) {
    object Projects : MenuNavigationItems("Projects", R.drawable.profile_icon, "projects")
    object Balance : MenuNavigationItems("Balance", R.drawable.profile_icon, "balance")
    object Settings : MenuNavigationItems("Settings", R.drawable.profile_icon, "settings")
    object FeedBack : MenuNavigationItems("FeedBack", R.drawable.profile_icon, "feedback")
}

sealed class AddItemNews(var titile: String, var screen_route: String) {
    object AddNews : AddItemNews("ScreenAddNews", "add_news")
}

sealed class ProjectItems(var title: String, var screen_route: String) {
    object AddProjectItem : ProjectItems("ScreenAddProject", "add_project")
    object InfoOfProject : ProjectItems("Screen info of project", "info_of_project")
}

sealed class InterView(var title: String, var screen_route: String) {
    object Interview : InterView("Interview", "interview")
    object AddOptionInterview : InterView("Add option interview", "app_option_interview")
}