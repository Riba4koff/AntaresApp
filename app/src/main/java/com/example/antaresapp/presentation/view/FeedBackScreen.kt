package com.example.antaresapp.presentation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.antaresapp.ui.theme.fontFamilyRoboto
import com.example.antaresapp.R
import com.example.antaresapp.presentation.Navigation.DrawerNavigation
import com.example.antaresapp.ui.theme.myColor
import kotlinx.coroutines.launch

@Composable
fun FeedBackScreen(scaffoldState: ScaffoldState, navController: NavController) {
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text("Обратная связь")
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
        }, drawerContent = {
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
        BodyFeedBackScreen(modifier = Modifier.padding(paddingValues = it))
    }
}

@Composable
fun BodyFeedBackScreen(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Связь с нами",
                fontFamily = fontFamilyRoboto,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
            )
        }
        Spacer(modifier = Modifier.padding(top = 16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {

                    },
                elevation = 5.dp,
                shape = RoundedCornerShape(10.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.feed_back_telephon),
                        contentDescription = "feed_back"
                    )
                    Spacer(modifier = Modifier.padding(start = 24.dp))
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            "8 800 555-35-35",
                            fontFamily = fontFamilyRoboto,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.padding(top = 8.dp))
                        Text(
                            "Бесплатный звонок если ты русский",
                            fontFamily = fontFamilyRoboto,
                            fontWeight = FontWeight.Normal,
                            fontSize = 10.sp
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.padding(top = 16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {

                    },
                elevation = 5.dp,
                shape = RoundedCornerShape(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.feedback),
                        contentDescription = "feed_back"
                    )
                    Spacer(modifier = Modifier.padding(start = 24.dp))
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            "Написать разработчикам",
                            fontFamily = fontFamilyRoboto,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.padding(top = 8.dp))
                        Text(
                            "Сообщить о проблеме или ошибке",
                            fontFamily = fontFamilyRoboto,
                            fontWeight = FontWeight.Normal,
                            fontSize = 10.sp
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.padding(top = 16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {

                    },
                elevation = 5.dp,
                shape = RoundedCornerShape(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.attach_file),
                        contentDescription = "feed_back"
                    )
                    Spacer(modifier = Modifier.padding(start = 24.dp))
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            "Отправить документы",
                            fontFamily = fontFamilyRoboto,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.padding(top = 8.dp))
                        Text(
                            "Приложить фото, загрузить файлы",
                            fontFamily = fontFamilyRoboto,
                            fontWeight = FontWeight.Normal,
                            fontSize = 10.sp
                        )
                    }
                }
            }
        }
    }
}