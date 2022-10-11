package com.example.antaresapp.presentation.Navigation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.antaresapp.*
import com.example.antaresapp.domain.*
import com.example.antaresapp.domain.models.UserInfo
import com.example.antaresapp.presentation.view.*
import com.example.antaresapp.presentation.viewModels.viewModels.OptionListViewModel
import com.example.antaresapp.presentation.viewModels.viewModels.ProjectViewModel
import com.example.antaresapp.presentation.viewModels.viewModels.ScreenModelsViewModel
import com.example.antaresapp.ui.theme.myColor

@SuppressLint("ComposableDestinationInComposeScope")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationGraph(
    navController: NavHostController,
    scaffoldState: ScaffoldState,
    projectViewModel: ProjectViewModel,
    screenModelsViewModel: ScreenModelsViewModel,
    optionListViewModel: OptionListViewModel = viewModel(factory = OptionListViewModel.Factory),
    userInfo: UserInfo
) {
    NavHost(navController, startDestination = BottomNavItem.News.screen_route) {
        //Новости - Scaffold сделан
        composable(BottomNavItem.News.screen_route) {
            NewsFeed(navController = navController, screenModelsViewModel, userInfo, scaffoldState)
        }
        //Календарь - Scaffold сделан
        composable(BottomNavItem.Calendar.screen_route) {
            CalendarScreen(scaffoldState = scaffoldState, navController = navController)
        }
        //Профиль - Scaffold сделан
        composable(BottomNavItem.Profile.screen_route) {
            ProfileScreen(userInfo, scaffoldState, navController)
        }
        //Проекты - Scaffold сделан
        composable(MenuNavigationItems.Projects.screen_route) {
            ShowListProject(
                navController = navController,
                viewModel = projectViewModel,
                user = userInfo,
                scaffoldState
            )
        }
        //Добавить проект - не надо
        composable(ProjectItems.AddProjectItem.screen_route) {
            InsertProjectItemScreen(
                navController = navController,
                scaffoldState = scaffoldState,
                viewModel = projectViewModel)
        }
        //Подробная информация о проекте   -  не надо
        composable(ProjectItems.InfoOfProject.screen_route) {
            InfoOfProjectScreen(
                navController = navController,
                projectItem = projectViewModel.getProjectItem(),
                scaffoldState = scaffoldState)
        }
        //Финансы - Сделан
        composable(MenuNavigationItems.Balance.screen_route) {
            FinanceScreen(scaffoldState, navController)
        }
        //Настройки сделаны
        composable(MenuNavigationItems.Settings.screen_route) {
            SettingsScreen(scaffoldState = scaffoldState, navController = navController)
        }
        //Обратная связь - сделан
        composable(MenuNavigationItems.FeedBack.screen_route) {
            FeedBackScreen(scaffoldState, navController)
        }
        //Добавить новость - не надо
        composable(AddItemNews.AddNews.screen_route) {
            AddNews(navController, scaffoldState, userInfo = UserInfo(), screenModelsViewModel)
        }
        //Создать опрос - не надо
        composable(Survey._Survey.screen_route) {
            AddSurvey(
                navController = navController,
                scaffoldState = scaffoldState,
                screenModelsViewModel = screenModelsViewModel,
                optionList = optionListViewModel,
                userInfo = userInfo
            )
        }
        //Выход из аккаунта
        composable(MenuNavigationItems.Exit.screen_route) {
            SignOutConfirmation(navController = navController)
        }
        //Экран входа - не надо
        composable(Login.LogIn.screen_route) {
            LogInScreen()
        }
    }
}

