package com.example.antaresapp.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.antaresapp.R
import com.example.antaresapp.domain.Task
import com.example.antaresapp.domain.models.TaskModel
import com.example.antaresapp.presentation.Navigation.DrawerNavigation
import com.example.antaresapp.presentation.viewModels.viewModels.TasksViewModel
import com.example.antaresapp.ui.theme.completeTaskColor
import com.example.antaresapp.ui.theme.myColor
import com.example.antaresapp.ui.theme.unCompleteTaskColor
import kotlinx.coroutines.launch

@Composable
fun TasksScreenNavigation(
    navController: NavController,
    tasksViewModel: TasksViewModel,
    scaffoldState: ScaffoldState,
) {
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text("Задачи")
                },
                backgroundColor = myColor,
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "tasks")
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
        TasksScreen(modifier = Modifier.padding(it), navController, tasksViewModel = tasksViewModel)
    }
}

@Composable
fun TasksScreen(modifier: Modifier, navController: NavController, tasksViewModel: TasksViewModel) {

    val stateTasksList by tasksViewModel.dataListTaskModel.observeAsState()

    LazyColumn(Modifier
        .fillMaxSize()
        .background(Color.White)) {
        item {
            Row(Modifier
                .fillMaxWidth()
                .padding(end = 4.dp, start = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(modifier = Modifier.size(24.dp),
                        painter = painterResource(id = com.example.antaresapp.R.drawable.sort),
                        contentDescription = "sort")
                }
                Button(modifier = Modifier.width(250.dp), onClick = {
                    navController.navigate(Task.AddTask.screen_route)
                }, colors = ButtonDefaults.buttonColors(myColor)) {
                    Text("Добавить задачу")
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(modifier = Modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.filter),
                        contentDescription = "filter")
                }
            }
        }

        itemsIndexed(stateTasksList!!) { index, item ->
            CardInfoTask(taskModel = item, tasksViewModel = tasksViewModel, navController)
        }

    }
}


@Composable
fun CardInfoTask(
    taskModel: TaskModel,
    tasksViewModel: TasksViewModel,
    navController: NavController
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 8.dp, end = 8.dp, bottom = 4.dp),
        backgroundColor = if (!taskModel.complete!!) unCompleteTaskColor else completeTaskColor
    ) {
        Row(Modifier
            .fillMaxWidth()
            .clickable(onClick = {
                tasksViewModel.setTask(taskModel)
                navController.navigate(Task.Info.screen_route)
{}            })
            .padding(4.dp)
        ) {
            Row(Modifier.fillMaxWidth(0.7f)) {
                Icon(modifier = Modifier.size(64.dp),
                    painter = painterResource(id = R.drawable.profile_icon),
                    contentDescription = "profile_icon")
                Column(Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp)) {
                    Text(text = taskModel.creator!!,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold)
                    Text(text = taskModel.description!!,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light)
                }
            }
            myColor
            Row(Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Top) {
                if (!taskModel.complete!!) Text(text = "Статус: Активно")
                else Text(modifier = Modifier.padding(top = 2.dp, end = 2.dp),
                    text = "Статус: Завершено",
                    fontSize = 12.sp)
            }
        }
    }
}