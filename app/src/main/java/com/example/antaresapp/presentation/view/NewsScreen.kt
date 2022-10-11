package com.example.antaresapp

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.antaresapp.domain.AddItemNews
import com.example.antaresapp.domain.BottomNavItem
import com.example.antaresapp.domain.MenuNavigationItems
import com.example.antaresapp.domain.Survey
import com.example.antaresapp.domain.models.ScreenModel
import com.example.antaresapp.domain.models.UserInfo
import com.example.antaresapp.domain.models.UserRights
import com.example.antaresapp.presentation.Navigation.DrawerNavigation
import com.example.antaresapp.presentation.Navigation.NavigationGraph
import com.example.antaresapp.presentation.view.CardNewsFeed
import com.example.antaresapp.presentation.view.CardSurvey
import com.example.antaresapp.presentation.viewModels.viewModels.ScreenModelsViewModel
import com.example.antaresapp.ui.theme.fontFamilyRoboto
import com.example.antaresapp.ui.theme.myColor
import kotlinx.coroutines.launch

////////////////////////////////////////////////////////////////////////////////////////////////////
//Новостная лента
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsFeed(
    navController: NavController,
    screenModelsViewModel: ScreenModelsViewModel,
    user: UserInfo,
    scaffoldState: ScaffoldState,
) {
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text("Новости")
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
        },
        drawerContent = {
            DrawerNavigation(navController = navController, scope = scope, closeDrawer = {
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            })
        },
        bottomBar = {
            com.example.antaresapp.presentation.Navigation.BottomNavigation(navController = navController)
        }
    )
    { paddingValues ->
        NewsFeedScroll(modifier = Modifier.padding(paddingValues = paddingValues),
            user = user,
            screenModelsViewModel = screenModelsViewModel,
            navController = navController)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsFeedScroll(
    modifier: Modifier,
    user: UserInfo,
    screenModelsViewModel: ScreenModelsViewModel,
    navController: NavController,
) {

    val stateModelList by screenModelsViewModel.dataScreenModelList.observeAsState()

    Column(modifier = Modifier.fillMaxHeight()) {
        Row(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = modifier) {
                if (user.rights == UserRights.User.right) {
                    /* BUTTONS UNAVAILABLE */
                    itemsIndexed(stateModelList?.reversed()!!,
                        key = { it, _ -> it }) { index, item ->
                        when (item) {
                            is ScreenModel.SurveyInfo -> CardSurvey(modifier = Modifier.animateItemPlacement(),
                                surveyInfo = item,
                                viewModel = screenModelsViewModel,
                                user = user)
                            is ScreenModel.NewsItem -> CardNewsFeed(modifier = Modifier.animateItemPlacement(),
                                newsItem = item,
                                user = user,
                                screenModelsViewModel)
                        }
                    }
                }
                if (user.rights == UserRights.Admin.right) {
                    item {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp, bottom = 8.dp),
                            horizontalArrangement = Arrangement.Center) {
                            Button(onClick = {
                                navController.navigate(AddItemNews.AddNews.screen_route)
                            },
                                colors = ButtonDefaults.buttonColors(myColor)) {
                                Text("Предложить новость")
                            }
                            Spacer(modifier = Modifier.padding(8.dp))
                            Button(onClick = { navController.navigate(Survey._Survey.screen_route) },
                                colors = ButtonDefaults.buttonColors(myColor)) {
                                Text("Добавить опрос")
                            }
                        }
                    }
                    itemsIndexed(stateModelList?.reversed()!!) { index, item ->
                        when (item) {
                            is ScreenModel.SurveyInfo -> CardSurvey(modifier = Modifier.animateItemPlacement(),
                                surveyInfo = item,
                                viewModel = screenModelsViewModel,
                                user = user)
                            is ScreenModel.NewsItem -> CardNewsFeed(modifier = Modifier.animateItemPlacement(),
                                newsItem = item,
                                user = user,
                                screenModelsViewModel)
                        }
                    }
                }
            }
        }
    }
}


