package com.example.antaresapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.antaresapp.domain.models.InterviewInfo
import com.example.antaresapp.domain.models.NewsItem
import com.example.antaresapp.domain.models.UserInfo
import com.example.antaresapp.presentation.Navigation.AddItemNews
import com.example.antaresapp.presentation.Navigation.BottomNavItem
import com.example.antaresapp.presentation.Navigation.InterView
import com.example.antaresapp.presentation.atTime
import com.example.antaresapp.presentation.viewModelimport.MainViewModel
import com.example.antaresapp.ui.theme.fontFamilyRoboto
import com.example.antaresapp.ui.theme.myColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//Новостная лента
@Composable
fun NewsFeed(
    navController: NavController,
    viewModel: MainViewModel,
    modifier: Modifier = Modifier,
) {

    val stateNewsList by viewModel.dataNewsList.observeAsState()

    Column(modifier = Modifier.fillMaxHeight()) {
        Row(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = modifier) {
                item {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 8.dp),
                        horizontalArrangement = Arrangement.Center) {
                        Button(onClick = { navController.navigate(AddItemNews.AddNews.screen_route) },
                            colors = ButtonDefaults.buttonColors(myColor)) {
                            Text("Предложить новость")
                        }
                        Spacer(modifier = Modifier.padding(8.dp))
                        Button(onClick = { navController.navigate(InterView.Interview.screen_route) },
                            colors = ButtonDefaults.buttonColors(myColor)) {
                            Text("Добавить опрос")
                        }
                    }
                }
                itemsIndexed(stateNewsList!!) { index, item ->
                    CardNewsFeed(newsItem = item)
                }
            }
        }
    }
}


//Вид новости в новостной ленте
@Composable
private fun CardNewsFeed(
    newsItem: NewsItem,
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp), elevation = 5.dp) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Icon(painter = painterResource(id = R.drawable.profile2),
                    contentDescription = "Icon",
                    modifier = Modifier.size(64.dp))
                Column(Modifier.fillMaxWidth()) {
                    Text(text = "${newsItem.name} ${newsItem.surname}",
                        fontSize = 16.sp,
                        fontFamily = fontFamilyRoboto,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 16.dp, bottom = 2.dp))

                    Text(text = "${newsItem.date}",
                        fontSize = 10.sp,
                        fontFamily = fontFamilyRoboto,
                        fontWeight = FontWeight.Thin,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 16.dp))
                }
            }
            Text(text = "${newsItem.description}",
                fontSize = 12.sp,
                fontFamily = fontFamilyRoboto,
                fontWeight = FontWeight.Light,
                color = Color.Black,
                modifier = Modifier.padding(top = 16.dp))
            newsItem.painter?.let { Image(painter = it, contentDescription = "Image") }
        }
    }

}

@Preview
@Composable
fun NewsFeedPreview() {
    val navController = rememberNavController()
    NewsFeed(navController = navController, viewModel = viewModel())
}

//Добавить новость
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InsertNewsScreen(
    navController: NavController,
    scaffoldState: ScaffoldState,
    userInfo: UserInfo,
    viewModel: MainViewModel,
) {
    var description by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)) {
        Column(modifier = Modifier.fillMaxHeight()) {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                IconButton(onClick = {
                    navController.navigate(BottomNavItem.News.screen_route)
                }, modifier = Modifier.padding(top = 4.dp)) {
                    Icon(painter = painterResource(id = R.drawable.arrow_back),
                        contentDescription = "Back",
                        modifier = Modifier.size(24.dp))
                }
                Spacer(modifier = Modifier.padding(start = 110.dp))
                IconButton(onClick = { /*TODO*/ }, modifier = Modifier.size(64.dp)) {
                    Icon(painter = painterResource(id = R.drawable.gallery),
                        contentDescription = "gallery",
                        modifier = Modifier.size(46.dp))
                }
                Button(modifier = Modifier.padding(top = 14.dp, end = 16.dp), onClick = {
                    if (description.isEmpty()) {
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("Заполните поле Описание")
                        }
                    } else {
                        viewModel.addNews(NewsItem(
                            name = userInfo.name,
                            surname = userInfo.surname,
                            description = description,
                            date = atTime()
                        ))
                        navController.navigate(BottomNavItem.News.screen_route)
                    }
                }, colors = ButtonDefaults.buttonColors(myColor)) {
                    Text("Сохранить")
                }
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                MyTextField(modifier = Modifier.width(320.dp),
                    value = description,
                    placeholder = "Описание",
                    onValueChange = {
                        description = it
                    })

            }
        }

    }
}

//Вид добавления опроса
@Composable
fun Interview(
    viewModel: MainViewModel,
    navController: NavController = rememberNavController(),
) {

    var nameInterview by remember { mutableStateOf("") }
    val deletedItemList = remember { mutableStateListOf<String>() }

    val stateOptionList by viewModel.dataOptionList.observeAsState()
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { navController.navigate(BottomNavItem.News.screen_route) }) {
                Icon(painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = "Назад",
                    modifier = Modifier.size(24.dp))
            }
        }

        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Card(Modifier
                .fillMaxWidth()
                .padding(8.dp)) {
                MyTextField(value = nameInterview,
                    onValueChange = { nameInterview = it },
                    placeholder = "Название опроса")
            }
        }

        Spacer(modifier = Modifier.padding(16.dp))

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(text = "Варианты ответа",
                fontFamily = fontFamilyRoboto,
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal)
        }

        Spacer(modifier = Modifier.padding(4.dp))

        LazyColumn(Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, bottom = 4.dp)) {
            itemsIndexed(items = stateOptionList!!.reversed()) { index, option ->
                AnimatedVisibility(visible = !deletedItemList.contains(index.toString()),
                    enter = fadeIn(),
                    exit = fadeOut(animationSpec = tween(75))) {

                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp, bottom = 4.dp),
                        elevation = 2.dp,
                        shape = RoundedCornerShape(15.dp)) {

                        Row(Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically) {
                            Text(text = option, modifier = Modifier.padding(start = 16.dp))

                            IconButton(onClick = {
                                val myIndex = stateOptionList!!.indexOf(option)
                                deletedItemList.add(index.toString())
                                scope.launch {
                                    delay(75)
                                    viewModel.deleteOption(myIndex)
                                }
                            }) {
                                Icon(modifier = Modifier.size(16.dp),
                                    painter = painterResource(id = R.drawable.delete_option),
                                    contentDescription = "delete_option")
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.padding(4.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(onClick = {
                navController.navigate(InterView.AddOptionInterview.screen_route)
            }, colors = ButtonDefaults.buttonColors(myColor)) {
                Text("Добавить вариант", fontSize = 14.sp)
            }
        }

    }
}


//Вид опроса в новостной ленте
@Composable
fun CardInterview(interviewInfo: InterviewInfo, viewModel: MainViewModel) {

    val stateOptionList by viewModel.dataOptionList.observeAsState()

    viewModel.addOption("Вариант 1")
    viewModel.addOption("Вариант 2")

    val state_person by remember { mutableStateOf("0") }

    val (selectedOption, onOptionSelected) = remember { mutableStateOf(stateOptionList!![0]) }
    var chosenItem by remember { mutableStateOf(-1) }
    var allow_click = true

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp), elevation = 5.dp) {
        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
                horizontalArrangement = Arrangement.Center) {
                Text(text = "${interviewInfo.nameInterview}",
                    fontFamily = fontFamilyRoboto,
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp)
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(text = "${interviewInfo.name} ${interviewInfo.surname}",
                    fontFamily = fontFamilyRoboto,
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp)
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)) {
                LazyColumn(Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, bottom = 4.dp)) {

                    itemsIndexed(stateOptionList!!) { index, item ->
                        AnimatedVisibility(visible = true) {
                            Card(modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 4.dp, bottom = 4.dp),
                                elevation = 2.dp,
                                shape = RoundedCornerShape(15.dp)) {
                                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                                    Row(modifier = Modifier
                                        .fillMaxWidth(0.9f)
                                        .padding(8.dp)
                                        .selectable(
                                            selected = (item == selectedOption),
                                            onClick = {
                                                if (allow_click) {
                                                    chosenItem = index
                                                    onOptionSelected(item)
                                                }
                                            },
                                        ),
                                        verticalAlignment = Alignment.CenterVertically) {
                                        AnimatedVisibility(visible = (index == chosenItem)){
                                            RadioButton(
                                                selected = (index == chosenItem),
                                                onClick = null
                                            )
                                        }
                                        Text(text = item,
                                            modifier = Modifier.padding(start = 16.dp))
                                    }
                                    Row(modifier = Modifier
                                        .height(40.5f.dp)
                                        .fillMaxWidth()
                                        .padding(end = 16.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.End
                                    ) {
                                        AnimatedVisibility(visible = (index == chosenItem),
                                            exit = fadeOut(animationSpec = tween(10)),
                                            enter = fadeIn(animationSpec = tween(10))
                                        ) {
                                            IconButton(onClick = {

                                                //При нажатии расчитывается процентаж и блокируются другие варианты,
                                                //Потом добавлю "Отмена голоса"
                                                //Блокировка делается с помощью allow_click, изначально равен true
                                                //После нажатия на выбранный элемент и нажатии на галочку
                                                //allow_click = false -> выбор больше невозможен
                                                allow_click = false

                                                //Добавляем при нажатии количество проголосовавших
                                                viewModel.addCountVotes()
                                                viewModel.setDataInOptionList()

                                                //выбранный элемент равен -1 и не равен текущему item
                                                //Что позволяет скрыть RadioButton и IconButton
                                                chosenItem = -1

                                            }) {
                                                Icon(modifier = Modifier.size(20.dp),
                                                    painter = painterResource(id = R.drawable.done),
                                                    contentDescription = "done")
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp), horizontalArrangement = Arrangement.Center) {
                Text(text = "Количество голосов: ${viewModel.getCountVotes()}",
                    fontFamily = fontFamilyRoboto,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal)
            }
        }
    }
}

//Добавить вариант в опрос
@Composable
fun AddInterviewOption(viewModel: MainViewModel, navController: NavController) {

    var titleOptionInterview by remember { mutableStateOf("") }

    val state by viewModel.dataOptionList.observeAsState()

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { navController.navigate(InterView.Interview.screen_route) }) {
                Icon(painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = "Назад",
                    modifier = Modifier.size(24.dp))
            }
            Button(onClick = {
                viewModel.addOption(titleOptionInterview)
                navController.navigate(InterView.Interview.screen_route)
            }, colors = ButtonDefaults.buttonColors(myColor)) {
                Text("Сохранить")
            }
        }
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Card(Modifier
                .fillMaxWidth()
                .padding(8.dp)) {
                MyTextField(value = titleOptionInterview,
                    onValueChange = { titleOptionInterview = it },
                    placeholder = "Добавить вариант опроса..")
            }
        }
    }
}