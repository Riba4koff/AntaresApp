package com.example.antaresapp.presentation.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.antaresapp.AnimationBox
import com.example.antaresapp.R
import com.example.antaresapp.domain.MenuNavigationItems
import com.example.antaresapp.domain.models.SubTaskModel
import com.example.antaresapp.domain.models.TaskModel
import com.example.antaresapp.presentation.viewModels.viewModels.SubTaskViewModel
import com.example.antaresapp.presentation.viewModels.viewModels.TasksViewModel
import com.example.antaresapp.ui.theme.subTaskColor
import kotlinx.coroutines.launch

@Composable
fun InfoAboutTask(
    navController: NavController,
    taskModel: TaskModel,
    tasksViewModel: TasksViewModel,
    subTaskViewModel: SubTaskViewModel,
) {
    var progress by remember { mutableStateOf(taskModel.progress) }
    var taskComplete by remember { mutableStateOf(taskModel.complete) }
    var expanded by remember { mutableStateOf(false) }
    var openButton by remember { mutableStateOf(taskModel.progress == 1f) }
    val scope = rememberCoroutineScope()

    LazyColumn {
        item {
            Row(Modifier.fillMaxWidth()) {
                Row(Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    IconButton(onClick = {
                        navController.navigate(MenuNavigationItems.Tasks.screen_route) {
                            popUpTo(MenuNavigationItems.Tasks.screen_route) {
                                inclusive = true
                            }
                        }
                    }) {
                        Icon(modifier = Modifier.size(24.dp),
                            painter = painterResource(id = com.example.antaresapp.R.drawable.arrow_back),
                            contentDescription = "go back")
                    }
                    IconButton(onClick = { expanded = true }) {
                        Icon(modifier = Modifier.size(24.dp),
                            painter = painterResource(id = com.example.antaresapp.R.drawable.more_vert),
                            contentDescription = "more")
                    }
                }
                Box(contentAlignment = Alignment.CenterEnd,
                    modifier = Modifier.padding(top = 16.dp)) {
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        Text("Редактировать", fontSize = 14.sp, modifier = Modifier
                            .padding(10.dp)
                            .clickable(onClick = {}))
                        Divider()
                        Text("Завершить досрочно", fontSize = 14.sp, modifier = Modifier
                            .padding(10.dp)
                            .clickable(onClick = {}))
                    }
                }
            }


        }
        item {
            Column(Modifier.fillMaxWidth()) {
                Divider(color = Color.Gray, thickness = 0.5.dp)
                Row(Modifier
                    .fillMaxWidth()
                    .padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Column(Modifier.fillMaxWidth(0.7f)) {
                        Text(taskModel.creator!!, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Text(taskModel.description!!,
                            fontWeight = FontWeight.Light,
                            fontSize = 11.sp)
                    }
                    Text(taskModel.timeAdding!!)
                }
                Row(Modifier
                    .fillMaxWidth()
                    .padding(8.dp), horizontalArrangement = Arrangement.Center) {
                    Text(taskModel.nameTask!!, fontWeight = FontWeight.Bold, fontSize = 24.sp)
                }
            }
            Row(Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp), horizontalArrangement = Arrangement.Center) {
                AnimatedVisibility(visible = progress == taskModel.progress) {
                    Text("Прогресс: ${((taskModel.progress)!! * 100).toInt()}%")
                }
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                AnimationBox {
                    LinearProgressIndicator(progress = progress!!, modifier = Modifier
                        .height(8.dp)
                        .width(250.dp)
                        .border(width = 0.5.dp, color = Color.Gray), color = Color.Green)
                }
            }
            Row(Modifier
                .fillMaxWidth()
                .padding(top = 4.dp), horizontalArrangement = Arrangement.Center) {
                AnimatedVisibility(visible = taskModel.complete!! == taskComplete) {
                    if (taskModel.complete!!) Text("Завершено")
                    else Text("Активно")
                }
            }
            Row(Modifier
                .fillMaxWidth()
                .padding(8.dp), horizontalArrangement = Arrangement.Center) {
                Text("Подзадачи", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
            }
        }
        itemsIndexed(taskModel.listSubTasks) { index, item ->
            var state by remember { mutableStateOf(item.state) }
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, bottom = 4.dp, top = 2.dp)) {
                Row(Modifier
                    .fillMaxWidth()
                    .background(color = subTaskColor),
                    verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = state, onCheckedChange = {
                        state = it
                        item.state = it
                        scope.launch {
                            progress = tasksViewModel.findProgress(taskModel.listSubTasks)
                            taskModel.progress = progress
                            openButton = progress == 1f
                        }
                    }, enabled = !taskComplete!!)
                    Text(item.nameSubTask, modifier = Modifier.padding(start = 4.dp))
                }
            }
        }

        item {

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                AnimationBox {
                    Button(onClick = {
                        taskComplete = true
                        taskModel.complete = true
                    },
                        modifier = Modifier.width(200.dp),
                        colors = ButtonDefaults.buttonColors(Color.LightGray),
                        enabled = openButton
                    ) {
                        Text("Завершить задачу")
                    }
                }
            }
        }
    }
}
