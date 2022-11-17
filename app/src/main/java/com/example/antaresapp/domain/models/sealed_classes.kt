package com.example.antaresapp.domain

import com.example.antaresapp.R

sealed class Login(var title: String, var screen_route: String) {
    object LogIn : Login("login_screen", "Login")
}

sealed class BottomNavItem(var title: String, var icon: Int, var screen_route: String) {
    object Calendar :
        BottomNavItem("Календарь", R.drawable.icon_calendar_bottom_navigation, "calendar")

    object Profile : BottomNavItem("Профиль", R.drawable.icon_profile_bottom_navigatin, "profile")
    object News : BottomNavItem("Новости", R.drawable.icon_news_bottom_navigation, "news")
}

sealed class MenuNavigationItems(
    var title: String,
    var description: String,
    var icon: Int,
    var screen_route: String,
) {
    object Calendar :
        MenuNavigationItems("Календарь",
            "Calendar item",
            R.drawable.icon_calendar_bottom_navigation,
            "calendar")

    object Profile : MenuNavigationItems("Профиль",
        "Profile item",
        R.drawable.icon_profile_bottom_navigatin,
        "profile")

    object News :
        MenuNavigationItems("Новостная лента", "News item", R.drawable.icon_news_bottom_navigation, "news")

    object Tasks : MenuNavigationItems("Задачи", "Tasks", R.drawable.tasks, "tasks")
    object Projects :
        MenuNavigationItems("Проекты", "Project item", R.drawable.projects, "projects")

    object Balance :
        MenuNavigationItems("Финансы", "Balance item", R.drawable.balance, "balance")

    object Settings :
        MenuNavigationItems("Настройки", "Settings item", R.drawable.settings, "settings")

    object FeedBack :
        MenuNavigationItems("Обратная связь", "FeedBack item", R.drawable.feedback, "feedback")

    object Exit : MenuNavigationItems("Выйти из аккаунта", "Exit item", R.drawable.sign_out, "sign_out")
}

sealed class AddItemNews(var titile: String, var screen_route: String) {
    object AddNews : AddItemNews("ScreenAddNews", "add_news")
}

sealed class AddTask(var title : String, var screen_route: String){
    object AddNewTask : AddTask("AddTaskScreen", "add_task")
}

sealed class ProjectItems(var title: String, var screen_route: String) {
    object AddProjectItem : ProjectItems("ScreenAddProject", "add_project")
    object InfoOfProject : ProjectItems("Screen info of project", "info_of_project")
}

sealed class Survey(var title: String, var screen_route: String) {
    object _Survey : Survey("Interview", "interview")
}
