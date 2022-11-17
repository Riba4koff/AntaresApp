package com.example.antaresapp.presentation.Navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.antaresapp.*
import com.example.antaresapp.domain.*
import com.example.antaresapp.domain.models.UserInfo
import com.example.antaresapp.presentation.view.*
import com.example.antaresapp.presentation.viewModels.viewModels.*

@SuppressLint("ComposableDestinationInComposeScope")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationGraph(
    navController: NavHostController,
    scaffoldState: ScaffoldState,
    projectViewModel: ProjectViewModel,
    screenModelsViewModel: ScreenModelsViewModel,
    tasksViewModel: TasksViewModel,
    subTaskViewModel: SubTaskViewModel,
    optionListViewModel: OptionListViewModel,
    userInfo: UserInfo,
) {
    NavHost(navController, startDestination = BottomNavItem.News.screen_route) {
        //Новости
        composable(BottomNavItem.News.screen_route) {
            NewsFeed(navController = navController, screenModelsViewModel, userInfo, scaffoldState)
        }
        //Календарь
        composable(BottomNavItem.Calendar.screen_route) {
            CalendarScreen(scaffoldState = scaffoldState, navController = navController)
        }
        //Профиль
        composable(BottomNavItem.Profile.screen_route) {
            ProfileScreen(userInfo, scaffoldState, navController)
        }
        //Задачи
        composable(MenuNavigationItems.Tasks.screen_route) {
            TasksScreenNavigation(navController = navController,
                tasksViewModel = tasksViewModel,
                scaffoldState = scaffoldState)
        }
        //Добавить задачу
        composable(AddTask.AddNewTask.screen_route) {
            AddTaskScreenNavigation(navController = navController,
                subTaskViewModel = subTaskViewModel,
                taskViewModel = tasksViewModel)
        }
        //Проекты - Scaffold сделан
        /*composable(MenuNavigationItems.Projects.screen_route) {
            ShowListProject(
                navController = navController,
                viewModel = projectViewModel,
                user = userInfo,
                scaffoldState
            )
        }*/
        //Добавить проект
        composable(ProjectItems.AddProjectItem.screen_route) {
            InsertProjectItemScreen(
                navController = navController,
                scaffoldState = scaffoldState,
                viewModel = projectViewModel)
        }
        //Подробная информация о проекте
        composable(ProjectItems.InfoOfProject.screen_route) {
            InfoOfProjectScreen(
                navController = navController,
                projectItem = projectViewModel.getProjectItem(),
                scaffoldState = scaffoldState)
        }
        //Финансы
        composable(MenuNavigationItems.Balance.screen_route) {
            FinanceScreen(scaffoldState, navController)
        }
        //Настройки
        composable(MenuNavigationItems.Settings.screen_route) {
            SettingsScreen(scaffoldState = scaffoldState, navController = navController)
        }
        //Обратная связь
        composable(MenuNavigationItems.FeedBack.screen_route) {
            FeedBackScreen(scaffoldState, navController)
        }
        //Добавить новость
        composable(AddItemNews.AddNews.screen_route) {
            AddNews(navController, scaffoldState, userInfo = UserInfo(), screenModelsViewModel)
        }
        //Создать опрос
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
        //Экран входа
        composable(Login.LogIn.screen_route) {
            LogInScreen(navController)
        }
    }
}

