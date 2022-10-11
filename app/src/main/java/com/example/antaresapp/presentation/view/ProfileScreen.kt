package com.example.antaresapp.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.antaresapp.MyText
import com.example.antaresapp.MyTextField
import com.example.antaresapp.R
import com.example.antaresapp.domain.models.UserInfo
import com.example.antaresapp.presentation.Navigation.DrawerNavigation
import com.example.antaresapp.ui.theme.fontFamilyRoboto
import com.example.antaresapp.ui.theme.myColor
import kotlinx.coroutines.launch

//Экран профиля
@Composable
fun ProfileScreen(
    userInfo: UserInfo = UserInfo(),
    scaffoldState: ScaffoldState,
    navController: NavController,
) {
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(title = {
                Text("Профиль")
            }, backgroundColor = myColor,
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
        BodyProfile(modifier = Modifier.padding(paddingValues = it), userInfo = userInfo)
    }

}

@Composable
fun BodyProfile(userInfo: UserInfo, modifier: Modifier) {
    var name by remember { mutableStateOf("Name") }
    var surname by remember { mutableStateOf("Surname") }
    var telegram by remember { mutableStateOf("") }
    var competition by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var project by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)
                .background(myColor)
                .shadow(elevation = 2.dp, shape = RectangleShape)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(1.dp)

            ) {
                Image(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(shape = CircleShape)
                        .background(myColor),
                    painter = painterResource(id = R.drawable.profile_icon),
                    contentDescription = "Profile",
                )
                TextProfile(text = "${userInfo.name}")
                TextProfile(text = "${userInfo.surname}")
            }
        }
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier) {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp, start = 8.dp, bottom = 4.dp, end = 8.dp),
                        shape = RoundedCornerShape(16.dp),
                        elevation = 4.dp
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            MyText(value = "Телеграм:")
                            MyTextField(
                                modifier = Modifier
                                    .padding(end = 20.dp, top = 2.dp)
                                    .width(200.dp),
                                value = telegram,
                                onValueChange = {
                                    telegram = it
                                    userInfo.project = telegram
                                }
                            )
                        }
                    }
                }
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp, start = 8.dp, bottom = 4.dp, end = 8.dp),
                        shape = RoundedCornerShape(16.dp),
                        elevation = 4.dp
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            MyText(value = "Специальность:")
                            MyTextField(
                                modifier = Modifier
                                    .padding(end = 20.dp, top = 2.dp)
                                    .width(200.dp),
                                value = competition,
                                onValueChange = {
                                    competition = it
                                    userInfo.competition = competition
                                }
                            )
                        }
                    }
                }
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp, start = 8.dp, bottom = 4.dp, end = 8.dp),
                        shape = RoundedCornerShape(16.dp),
                        elevation = 4.dp
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            MyText(value = "Возраст: ")
                            MyTextField(
                                modifier = Modifier
                                    .padding(end = 20.dp, top = 2.dp)
                                    .width(200.dp),
                                value = age,
                                onValueChange = {
                                    age = it
                                    userInfo.age = age
                                }
                            )
                        }
                    }
                }
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp, start = 8.dp, bottom = 4.dp, end = 8.dp),
                        shape = RoundedCornerShape(16.dp),
                        elevation = 4.dp
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            MyText(value = "email:")
                            MyTextField(
                                modifier = Modifier
                                    .padding(end = 20.dp, top = 2.dp)
                                    .width(200.dp),
                                value = email,
                                onValueChange = {
                                    email = it
                                    userInfo._mail = email
                                }
                            )
                        }
                    }
                }
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp, start = 8.dp, bottom = 4.dp, end = 8.dp),
                        shape = RoundedCornerShape(16.dp),
                        elevation = 4.dp
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            MyText(value = "Текущие проекты:")
                            MyTextField(
                                modifier = Modifier
                                    .padding(end = 20.dp, top = 4.dp)
                                    .width(200.dp),
                                value = project,
                                onValueChange = {
                                    project = it
                                    userInfo.project = project
                                }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.padding(top = 16.dp))
                }
                item {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        Button(
                            onClick = { /*TODO*/ },

                            ) {
                            Text(
                                text = "ПОДРОБНЕЕ",
                                fontFamily = fontFamilyRoboto,
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                    Spacer(modifier = Modifier.padding(bottom = 20.dp))
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Bottom
                    ) {

                    }
                }
            }
        }
    }

}

@Composable
private fun TextProfile(
    text: String,
) {
    Text(
        text = text,
        fontFamily = fontFamilyRoboto,
        fontWeight = FontWeight.Medium,
        color = Color.White,
        modifier = Modifier,
        fontSize = 28.sp,
        letterSpacing = 1.3.sp
    )
}
