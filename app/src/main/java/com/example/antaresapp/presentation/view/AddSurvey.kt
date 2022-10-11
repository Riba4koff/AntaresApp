package com.example.antaresapp

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.antaresapp.domain.BottomNavItem
import com.example.antaresapp.domain.models.OptionModel
import com.example.antaresapp.domain.models.ScreenModel
import com.example.antaresapp.domain.models.UserInfo
import com.example.antaresapp.presentation.atTime
import com.example.antaresapp.presentation.viewModels.viewModels.OptionListViewModel
import com.example.antaresapp.presentation.viewModels.viewModels.ScreenModelsViewModel
import com.example.antaresapp.ui.theme.myColor
import kotlinx.coroutines.launch

//Добавить опрос
@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddSurvey(
    modifier: Modifier = Modifier,
    optionList: OptionListViewModel?,
    screenModelsViewModel: ScreenModelsViewModel,
    navController: NavController = rememberNavController(),
    scaffoldState: ScaffoldState,
    userInfo: UserInfo,
) {

    var nameSurvey by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val stateOptionList by optionList?.dataOptionList!!.observeAsState()
    val copyOptionList = stateOptionList?.toList()

    val scope = rememberCoroutineScope()

    LazyColumn(modifier
        .background(Color.White)) {
        item {
            Row(Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {
                    navController.navigate(BottomNavItem.News.screen_route){
                        popUpTo(BottomNavItem.News.screen_route){
                            inclusive = true
                        }
                    }
                }) {
                    Icon(modifier = Modifier.size(20.dp),
                        painter = painterResource(id = R.drawable.arrow_back),
                        contentDescription = "Назад")
                }
                Button(onClick = {
                    if (nameSurvey.isEmpty() && description.isEmpty()) {
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("Заполните все поля")
                        }
                    } else if (nameSurvey.isEmpty()) {
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("Заполните поле название")
                        }
                    } else if (description.isEmpty()) {
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("Заполните поле описание")
                        }
                    } else if (copyOptionList!!.isEmpty()) {
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("Добавьте хотя бы 2 варианта")
                        }
                    } else if (copyOptionList.size == 1) {
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("Добавьте еще 1 вариант")
                        }
                    } else {
                        screenModelsViewModel.saveSurvey(
                            ScreenModel.SurveyInfo(
                                name = userInfo.name,
                                surname = userInfo.surname,
                                nameSurvey = nameSurvey,
                                description = description,
                                votes = 0,
                                date = atTime(),
                                listOptionItem = copyOptionList
                            )
                        )
                        optionList?.clear()
                        navController.navigate(BottomNavItem.News.screen_route){
                            popUpTo(BottomNavItem.News.screen_route) {
                                inclusive = true
                            }
                        }
                    }
                }, colors = ButtonDefaults.buttonColors(myColor)) {
                    Text("Сохранить")
                }
            }
        }
        item {
            Row(Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)) {
                MyTextField(value = nameSurvey,
                    placeholder = "Введите название опроса",
                    onValueChange = {
                        nameSurvey = it
                    })
            }
            Divider(modifier = Modifier.padding(start = 32.dp, end = 16.dp),
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f),
                thickness = 1.dp)
        }
        item {
            Row(Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)) {
                MyTextField(value = description,
                    placeholder = "Введите описание опроса",
                    onValueChange = {
                        description = it
                    })
            }
            Divider(modifier = Modifier.padding(start = 32.dp, end = 16.dp),
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f),
                thickness = 1.dp)
        }
        item {
            var index by remember { mutableStateOf(0) }
            Row(Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                var option by remember { mutableStateOf("") }
                MyTextField(modifier = Modifier
                    .padding(top = 3.dp)
                    .width(250.dp),
                    value = option,
                    placeholder = "Название варианта",
                    onValueChange = { item ->
                        option = item
                    })

                Button(colors = ButtonDefaults.buttonColors(myColor), onClick = {
                    if (option.isEmpty()) {
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("Введите название варианта")
                        }
                    } else {
                        optionList?.addOption(0, OptionModel(id = index, option, 0, 0f))
                        index++
                        option = ""
                    }
                }) {
                    Text("Добавить")
                }
            }
        }
        itemsIndexed(stateOptionList!!, key = { it, _-> it }) { index, item ->
            AnimationBox {
                Row(Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 4.dp, bottom = 4.dp)
                    .animateItemPlacement(animationSpec = tween(200)),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp)) {
                        Row(Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically) {
                            Text(item.option, modifier = Modifier.padding(start = 8.dp))
                            IconButton(onClick = {
                                optionList?.deleteOption(index)
                            }) {
                                Icon(modifier = Modifier.size(20.dp),
                                    painter = painterResource(id = R.drawable.delete),
                                    contentDescription = "delete option")
                            }
                        }
                    }
                }
            }
        }


    }
}

//Анимация добавления
@Composable
fun <T> T.AnimationBox(
    enter: EnterTransition = fadeIn(),
    exit: ExitTransition = fadeOut() + shrinkVertically(),
    content: @Composable T.() -> Unit,
) {
    val state = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = true
        }
    }
    AnimatedVisibility(
        visibleState = state,
        enter = enter,
        exit = exit
    ) { content() }
}
