package com.example.antaresapp.presentation.Navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.antaresapp.domain.BottomNavItem
import com.example.antaresapp.domain.Login
import com.example.antaresapp.domain.MenuNavigationItems
import com.example.antaresapp.ui.theme.fontFamilyRoboto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//Навигация левого меню
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DrawerNavigation(
    navController: NavController,
    scope: CoroutineScope,
    closeDrawer: () -> Unit = {},
    openDialog: () -> Unit = {}
) {
    val listOfMenuItems = listOf(
        MenuNavigationItems.Profile,
        //MenuNavigationItems.Projects,
        MenuNavigationItems.Tasks,
        MenuNavigationItems.Calendar,
        MenuNavigationItems.Balance,
        MenuNavigationItems.News,
        MenuNavigationItems.Settings,
        MenuNavigationItems.FeedBack,
        MenuNavigationItems.Exit
    )
    DrawerHeader()
    DrawerBody(listOfMenuItems = listOfMenuItems,
        navController = navController,
        scope = scope,
        closeDrawer = closeDrawer,
        openDialog = openDialog)
}
