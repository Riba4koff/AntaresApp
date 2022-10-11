@file:Suppress("UNUSED_EXPRESSION")

package com.example.antaresapp

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.antaresapp.domain.MenuNavigationItems
import com.example.antaresapp.domain.ProjectItems
import com.example.antaresapp.domain.models.ProjectItem
import com.example.antaresapp.domain.models.UserInfo
import com.example.antaresapp.domain.models.UserRights
import com.example.antaresapp.presentation.Navigation.DrawerNavigation
import com.example.antaresapp.presentation.viewModels.viewModels.ProjectViewModel
import com.example.antaresapp.ui.theme.fontFamilyRoboto
import com.example.antaresapp.ui.theme.myColor
import kotlinx.coroutines.launch


//Список карточек проектов
@Composable
fun ShowListProject(
    navController: NavController,
    viewModel: ProjectViewModel,
    user : UserInfo,
    scaffoldState: ScaffoldState
){
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text("Проекты")
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
    ) {
        BodyProjectScreen(navController = navController, viewModel = viewModel, user = user, modifier = Modifier.padding(paddingValues = it))
    }
}


@Composable
fun BodyProjectScreen(
    navController: NavController,
    viewModel: ProjectViewModel,
    user : UserInfo,
    modifier :Modifier
){
    val stateProjectList by viewModel.dataProjectList.observeAsState()

    LazyColumn(
        modifier = modifier
    ) {
        if (user.rights == UserRights.Admin.right){
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            navController.navigate(ProjectItems.AddProjectItem.screen_route)
                        },
                        colors = ButtonDefaults.buttonColors(myColor)
                    ) {
                        Text("Добавить проект", color = Color.White)
                    }
                }
            }
        }
        itemsIndexed(
            stateProjectList!!.reversed()
        ) { index, item ->
            OptionsProjectList(projectItem = item, navController, projectViewModel = viewModel)
        }
    }
}

//вид карточки проекта
@Composable
fun OptionsProjectList(
    projectItem: ProjectItem,
    navController: NavController,
    projectViewModel: ProjectViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable {
                    projectViewModel.setProjectItem(projectItem)
                    navController.navigate(ProjectItems.InfoOfProject.screen_route)
                }
                .height(90.dp),
            shape = RoundedCornerShape(5.dp),
            elevation = 5.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 6.dp, start = 9.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    //Project name
                    Text(
                        text = "${projectItem.nameProject}",
                        fontSize = 16.sp,
                        fontFamily = fontFamilyRoboto,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    //Description
                    Box(
                        modifier = Modifier
                            .width(150.dp)
                            .height(45.dp)
                    ) {
                        Text(
                            text = "${projectItem.description}",
                            fontSize = 10.sp,
                            fontFamily = fontFamilyRoboto,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black
                        )
                    }
                }
                Column(Modifier.fillMaxHeight()) {
                    Box(
                        modifier = Modifier
                            .padding(start = 50.dp)
                            .height(45.dp)
                            .width(135.dp)
                    ) {
                        Text(
                            text = "Сроки проекта: ${projectItem.dateProject}",
                            color = Color.Black,
                            fontFamily = fontFamilyRoboto,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    }
                    Box(
                        modifier = Modifier
                            .height(45.dp)
                            .width(150.dp)
                            .padding(start = 50.dp)

                    ) {
                        Text(
                            text = "Кол-во людей в проекте: ${projectItem.countPerson}",
                            color = Color.Black,
                            fontFamily = fontFamilyRoboto,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                        )
                    }
                }
            }
        }
    }
}
