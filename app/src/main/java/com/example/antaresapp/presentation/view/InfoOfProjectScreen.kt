package com.example.antaresapp.presentation.view

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.antaresapp.AnimationBox
import com.example.antaresapp.MyTextField
import com.example.antaresapp.R
import com.example.antaresapp.domain.MenuNavigationItems
import com.example.antaresapp.domain.models.TaskModelNeTrogat
import com.example.antaresapp.domain.models.ProjectItem
import com.example.antaresapp.presentation.viewModels.viewModels.InfoOfProjectViewModel
import com.example.antaresapp.ui.theme.myColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InfoOfProjectScreen(
    navController: NavController,
    projectItem: ProjectItem,
    scaffoldState: ScaffoldState,
    infoOfProjectViewModel: InfoOfProjectViewModel = InfoOfProjectViewModel(projectItem),
) {
    BodyInfoOfProjectScreen(navController = navController,
        projectItem = projectItem,
        scaffoldState = scaffoldState,
        infoOfProjectViewModel = infoOfProjectViewModel)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BodyInfoOfProjectScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    projectItem: ProjectItem,
    scaffoldState: ScaffoldState,
    infoOfProjectViewModel: InfoOfProjectViewModel
){
    var nameProject by remember { mutableStateOf(projectItem.nameProject) }
    var description by remember { mutableStateOf(projectItem.description) }
    var date by remember { mutableStateOf(projectItem.dateProject) }
    var count_person by remember { mutableStateOf(projectItem.countPerson) }
    var intention by remember { mutableStateOf(projectItem.intention) }

    var id_task by remember { mutableStateOf(-1) }

    val scope = rememberCoroutineScope()

    val stateListTasks by infoOfProjectViewModel.dataListTasks.observeAsState()

    LazyColumn(modifier = modifier) {
        item {
            Row(Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {
                IconButton(modifier = Modifier.padding(top = 4.dp, start = 4.dp), onClick = {
                    navController.navigate(MenuNavigationItems.Projects.screen_route)
                }) {
                    Icon(modifier = Modifier.size(20.dp),
                        painter = painterResource(id = R.drawable.arrow_back),
                        contentDescription = "back")
                }
                OutlinedButton(modifier = Modifier.padding(end = 8.dp),
                    colors = ButtonDefaults.buttonColors(myColor),
                    onClick = {
                        projectItem.dateProject = date
                        projectItem.nameProject = nameProject
                        projectItem.description = description
                        projectItem.countPerson = count_person
                        projectItem.intention = intention
                    },
                    shape = RoundedCornerShape(15.dp)) {
                    Text("??????????????????")
                }
            }
            Divider(color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f), thickness = 1.dp)
        }
        //???????????????? ??????????????
        item {
            RowForProjectFields(value = nameProject!!,
                text = "???????????????? ??????????????",
                placeholder = "?????????????? ????????????????",
                onValueChange = {
                    nameProject = it
                })
            Divider(color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f), thickness = 1.dp)
        }
        //???????????????? ??????????????
        item {
            RowForProjectFields(value = description!!,
                text = "???????????????? ??????????????",
                placeholder = "?????????????? ????????????????",
                onValueChange = {
                    description = it
                })
            Divider(color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f), thickness = 1.dp)
        }
        //?????????? ??????????????
        item {
            RowForProjectFields(value = date!!,
                text = "?????????? ??????????????",
                placeholder = "?????????????? ??????????",
                onValueChange = {
                    date = it
                })
            Divider(color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f), thickness = 1.dp)
        }
        //???????????????????? ????????????????????
        item {
            RowForProjectFields(value = count_person!!,
                text = "???????????????????? ????????????????????",
                placeholder = "?????????????? ???????????????????? ????????????????????",
                onValueChange = {
                    count_person = it
                })
            Divider(color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f), thickness = 1.dp)
        }
        //???????? ??????????????
        item {
            RowForProjectFields(value = intention!!,
                text = "???????? ??????????????",
                placeholder = "?????????????? ????????",
                onValueChange = {
                    intention = it
                })
            Divider(color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f), thickness = 1.dp)
        }
        //???????????? ??????????????
        item {
            Spacer(Modifier.padding(top = 8.dp))
            Row(Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                Text("???????????? ??????????????", fontSize = 24.sp)
            }

        }
        //???????????????? ???????????? ?? ???????????? ???????????????? ????????????
        item {
            var adding_item by remember { mutableStateOf("") }
            Row(Modifier
                .fillMaxWidth()
                .padding(4.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                MyTextField(modifier = Modifier.width(200.dp),
                    value = adding_item,
                    onValueChange = {
                        adding_item = it
                    },
                    placeholder = "???????????????? ????????????")
                Button(modifier = Modifier.padding(), onClick = {
                    scope.launch {
                        if (adding_item == "") {
                            scaffoldState.snackbarHostState.showSnackbar("?????????????? ???????????????? ????????????")
                        } else {
                            id_task += 1
                            infoOfProjectViewModel.addTask(project = projectItem,
                                TaskModelNeTrogat(id_task, adding_item, false),
                                index = 0)
                            adding_item = ""
                        }
                    }
                }, colors = ButtonDefaults.buttonColors(myColor)) {
                    Text("???????????????? ????????????", fontSize = 13.sp)
                }
            }
            Divider(color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f), thickness = 1.dp)
        }
        itemsIndexed(items = stateListTasks!!, key = { it, _ -> it }) { index, item ->
            var checkedState by remember { mutableStateOf(item.state) }
            AnimationBox {
                Row(Modifier
                    .fillMaxWidth()
                    .height(47.dp)
                    .animateItemPlacement(animationSpec = tween(600)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Box(modifier = Modifier.fillMaxWidth(0.5f)) {
                        Row(Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically) {

                            Checkbox(checked = checkedState, onCheckedChange = {
                                checkedState = it
                                item.state = checkedState
                            })

                            Text(item.nameTask!!, fontSize = 16.sp)
                        }
                    }
                    IconButton(onClick = {
                        scope.launch {
                            delay(100)
                            infoOfProjectViewModel.deleteTask(projectItem, index)
                        }
                    }) {
                        Icon(modifier = Modifier.size(20.dp),
                            painter = painterResource(id = R.drawable.delete),
                            contentDescription = "delete_task")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun TestInfo() {
    InfoOfProjectScreen(navController = rememberNavController(),
        projectItem = ProjectItem(),
        scaffoldState = rememberScaffoldState())
}

@Composable
fun RowForProjectFields(
    value: String,
    onValueChange: (String) -> Unit = {},
    text: String,
    placeholder: String,
) {
    val focus = LocalFocusManager.current
    Row(Modifier
        .fillMaxWidth()
        .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = text, modifier = Modifier.padding(start = 16.dp))
        TextField(
            modifier = Modifier
                .width(160.dp)
                .padding(top = 8.dp),
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(text = placeholder, fontSize = 13.sp)
            },
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                focus.clearFocus()
            }),
        )
    }
}
