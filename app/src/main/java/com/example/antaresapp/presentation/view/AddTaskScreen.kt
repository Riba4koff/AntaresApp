package com.example.antaresapp.presentation.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.antaresapp.AnimationBox
import com.example.antaresapp.MyTextField
import com.example.antaresapp.R
import com.example.antaresapp.domain.MenuNavigationItems
import com.example.antaresapp.domain.models.SubTaskModel
import com.example.antaresapp.domain.models.TaskModel
import com.example.antaresapp.presentation.atTime
import com.example.antaresapp.presentation.viewModels.viewModels.SubTaskViewModel
import com.example.antaresapp.presentation.viewModels.viewModels.TasksViewModel
import com.example.antaresapp.ui.theme.myColor
import com.example.antaresapp.ui.theme.subTaskColor

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddTaskScreenNavigation(
    navController: NavController,
    subTaskViewModel: SubTaskViewModel,
    taskViewModel: TasksViewModel,
) {
    Scaffold(

    ) {
        AddTaskScreen(modifier = Modifier.padding(it),
            navController = navController,
            subTaskViewModel = subTaskViewModel,
            taskViewModel = taskViewModel
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddTaskScreen(
    modifier: Modifier,
    navController: NavController,
    subTaskViewModel: SubTaskViewModel,
    taskViewModel: TasksViewModel,
) {

    var nameTask by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var addSubTask by remember { mutableStateOf("") }

    val stateSubTaskList by subTaskViewModel.dataSubTaskList.observeAsState()


    LazyColumn(Modifier
        .fillMaxSize()
        .background(Color.White)) {
        item {
            Row(Modifier
                .fillMaxWidth()
                .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                IconButton(onClick = {
                    navController.navigate(MenuNavigationItems.Tasks.screen_route) {
                        popUpTo(MenuNavigationItems.Tasks.screen_route) {
                            inclusive = true
                        }
                    }
                }) {
                    Icon(modifier = Modifier.size(24.dp),
                        painter = painterResource(id = com.example.antaresapp.R.drawable.arrow_back),
                        contentDescription = "arrow_back")
                }
                Button(onClick = {
                    taskViewModel.addTask(
                        TaskModel(
                            nameTask = nameTask,
                            description = description,
                            creator = "Creator",
                            executor = "Executor",
                            timeAdding = atTime(),
                            listSubTasks = stateSubTaskList!!.toList(),
                            progress = 0f,
                            complete = false
                        )
                    )
                    subTaskViewModel.clear()
                    navController.navigate(MenuNavigationItems.Tasks.screen_route){
                        popUpTo(MenuNavigationItems.Tasks.screen_route){
                            inclusive = true
                        }
                    }
                }, colors = ButtonDefaults.buttonColors(myColor)) {
                    Text(text = "Сохранить")
                }
            }
        }
        item {
            Divider(color = Color.Gray, thickness = 0.5.dp)
            Row(Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                Text(modifier = Modifier.padding(start = 16.dp),
                    text = "Название задачи:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal)
                MyTextField(modifier = Modifier.width(170.dp),
                    value = nameTask,
                    onValueChange = {
                        nameTask = it
                    },
                    placeholder = "Введите название",
                    horizontalArrangement = Arrangement.End
                )
            }
            Divider(color = Color.Gray, thickness = 0.5.dp)
        }
        item {
            Row(Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                Text(modifier = Modifier.padding(start = 16.dp),
                    text = "Описание:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal)
                MyTextField(modifier = Modifier.width(170.dp),
                    value = description,
                    onValueChange = {
                        description = it
                    },
                    placeholder = "Введите описание",
                    horizontalArrangement = Arrangement.End
                )
            }
            Divider(color = Color.Gray, thickness = 0.5.dp)
        }
        item {
            val focus = LocalFocusManager.current
            Row(Modifier
                .fillMaxWidth()
                .padding(top = 4.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                TextField(
                    modifier = Modifier.width(200.dp),
                    value = addSubTask,
                    onValueChange = {
                        addSubTask = it
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        focus.clearFocus()
                    }),
                    singleLine = false,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    placeholder = {
                        Text("Название подзадачи")
                    }
                )
                Button(modifier = Modifier
                    .padding(end = 8.dp)
                    .width(200.dp),
                    onClick = {
                        subTaskViewModel.addSubTask(
                            SubTaskModel(
                                nameSubTask = addSubTask,
                                state = false
                            )
                        )
                        addSubTask = ""
                    },
                    colors = ButtonDefaults.buttonColors(
                        myColor)) {
                    Text("Добавить")
                }
            }
        }

        itemsIndexed(stateSubTaskList!!) { index, item ->
            var checked by remember { mutableStateOf(item.state) }
            AnimationBox {
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, bottom = 4.dp),
                    backgroundColor = subTaskColor
                ) {
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Row(Modifier.fillMaxWidth(0.85f)){
                            Text(modifier = Modifier.padding(start = 8.dp), text = item.nameSubTask)
                        }
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                            IconButton(onClick = {
                                subTaskViewModel.deleteSubTask(item);
                            }) {
                                Icon(modifier = Modifier.size(24.dp),
                                    painter = painterResource(id = R.drawable.delete),
                                    contentDescription = "delete subtask")
                            }
                        }
                    }
                }
            }
        }
    }
}
