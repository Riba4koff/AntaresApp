package com.example.antaresapp.presentation.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.antaresapp.*
import com.example.antaresapp.domain.MenuNavigationItems
import com.example.antaresapp.domain.models.ProjectItem
import com.example.antaresapp.presentation.Navigation.DrawerNavigation
import com.example.antaresapp.presentation.viewModels.viewModels.ProjectViewModel
import com.example.antaresapp.ui.theme.fontFamilyRoboto
import com.example.antaresapp.ui.theme.myColor
import kotlinx.coroutines.launch


@Composable
fun InsertProjectItemScreen(
    navController: NavController,
    scaffoldState: ScaffoldState,
    viewModel: ProjectViewModel
) {
   BodyInsertOfProjectScreen(navController = navController, scaffoldState = scaffoldState, viewModel = viewModel)
}

@Composable
fun BodyInsertOfProjectScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState,
    viewModel: ProjectViewModel
){
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
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    scope.launch {
                        navController.navigate(MenuNavigationItems.Projects.screen_route){
                            popUpTo(MenuNavigationItems.News.screen_route)
                        }
                    }
                }) {
                    Icon(modifier = Modifier.size(24.dp) ,painter = painterResource(id = com.example.antaresapp.R.drawable.arrow_back), contentDescription = "back")
                }
                Spacer(modifier = Modifier.padding(start = 70.dp))
                Button(
                    modifier = Modifier,
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

                            navController.navigate(MenuNavigationItems.Projects.screen_route){
                                popUpTo(MenuNavigationItems.Projects.screen_route)
                            }
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