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
import com.example.antaresapp.domain.models.ProjectItem
import com.example.antaresapp.presentation.Navigation.ProjectItems
import com.example.antaresapp.presentation.Navigation.MenuNavigationItems
import com.example.antaresapp.presentation.viewModelimport.MainViewModel
import com.example.antaresapp.ui.theme.fontFamilyRoboto
import com.example.antaresapp.ui.theme.myColor
import kotlinx.coroutines.launch


//Список карточек проектовю
@Composable
fun ShowListProject(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {

    val stateProjectList by viewModel.dataProjectList.observeAsState()

    LazyColumn(
        modifier = modifier
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { navController.navigate(ProjectItems.AddProjectItem.screen_route) },
                    colors = ButtonDefaults.buttonColors(myColor)
                ) {
                    Text("Добавить проект", color = Color.White)
                }
            }
        }
        itemsIndexed(
            stateProjectList!!.reversed()
        ) { index, item ->
            OptionsProjectList(projectItem = item, navController)
        }
    }
}

//вид карточки проекта
@Composable
fun OptionsProjectList(
    projectItem: ProjectItem,
    navController: NavController
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


@Composable
fun InsertProjectItemScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState,
    viewModel: MainViewModel
) {

    val scope = rememberCoroutineScope()
    var projectName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var countPerson by remember { mutableStateOf("") }
    val focus = LocalFocusManager.current


    Box(modifier = modifier) {
        Column(
            modifier = modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {
                        navController.navigate(MenuNavigationItems.Projects.screen_route)
                    },
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_back),
                        contentDescription = "Back",
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.padding(start = 70.dp))
                Button(
                    modifier = Modifier.padding(top = 14.dp),
                    onClick = {
                        if (description.length < 11 || date.length < 21 || (countPerson.isEmpty() || countPerson.toInt() < 0) || projectName.length < 3) {
                            scope.launch {
                                scaffoldState.snackbarHostState.showSnackbar("Проверьте правильность заполненных полей")
                            }
                        } else {

                            viewModel.addProject(ProjectItem(
                                nameProject = projectName,
                                description = description,
                                dateProject = date,
                                countPerson = countPerson
                            ))

                            navController.navigate(MenuNavigationItems.Projects.screen_route)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(myColor)
                ) {
                    Text("Сохранить")
                }
            }
            Spacer(modifier = Modifier.padding(8.dp))
            MyRow(
                placeholder = "Название проекта",
                value = projectName,
                onValueChange = { newText ->
                    projectName = newText.trimStart { it == '0' }
                })
            //////////
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp)){
                if (projectName.length < 3){
                    Text(
                        "Не менее 3 символов",
                        fontFamily = fontFamilyRoboto,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray
                    )
                } else Text("")
            }
            //////////
            Spacer(modifier = Modifier.padding(8.dp))
            MyRow(placeholder = "Описание", value = description, onValueChange = { newText ->
                description = newText.trimStart { it == '0' }
            })
            /////////
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp)){
                if (description.length < 11){
                    Text(
                        "Не менее 11 символов",
                        fontFamily = fontFamilyRoboto,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray
                    )
                } else Text("")
            }
            ////////
            Spacer(modifier = Modifier.padding(8.dp))
            MyRow(
                placeholder = "Сроки проекта (дд.мм.гггг-дд.мм.гггг)",
                value = date,
                onValueChange = { newText ->
                    date = newText.trimStart { it == '0' }
                })
            /////////
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp)){
                if (date.length < 21){
                    Text(
                        "Не менее 21 символа",
                        fontFamily = fontFamilyRoboto,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray
                    )
                } else Text("")
            }
            ////////
            Spacer(modifier = Modifier.padding(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(BorderStroke(2.dp, myColor))
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 4.dp)
            ) {
                TextField(
                    modifier = Modifier.width(320.dp),
                    value = countPerson,
                    placeholder = { Text("Количество участников") },
                    onValueChange = { newText ->
                        countPerson = newText.trimStart { it == '0' }
                    }, keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Number
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
                    )
                )
            }
            ///////////
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp)){
                if (countPerson.isEmpty()){
                    Text(
                        "не менее 1",
                        fontFamily = fontFamilyRoboto,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray
                    )
                } else Text("")
            }
            ////////
        }
    }
}

@Composable
private fun MyRow(placeholder: String, onValueChange: (String) -> Unit = {}, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(BorderStroke(2.dp, myColor))
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 4.dp)
    ) {
        MyTextField(
            modifier = Modifier.width(320.dp),
            value = value,
            placeholder = placeholder,
            onValueChange = onValueChange
        )
    }
}
