package com.example.antaresapp


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.antaresapp.domain.BottomNavItem
import com.example.antaresapp.domain.models.ScreenModel
import com.example.antaresapp.domain.models.UserInfo
import com.example.antaresapp.presentation.atTime
import com.example.antaresapp.presentation.viewModels.viewModels.ScreenModelsViewModel
import com.example.antaresapp.ui.theme.myColor
import kotlinx.coroutines.launch


//Добавить новость
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddNews(
    navController: NavController,
    scaffoldState: ScaffoldState,
    userInfo: UserInfo,
    viewModel: ScreenModelsViewModel,
) {
    var description by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)) {
        Column(modifier = Modifier.fillMaxHeight()) {
            Row(modifier = Modifier.fillMaxWidth().padding(start = 4.dp, end = 2.dp),
                horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {
                    navController.navigate(BottomNavItem.News.screen_route){
                        popUpTo(BottomNavItem.News.screen_route){
                            inclusive = true
                        }
                    }
                }) {
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
            }
            Box(modifier = Modifier.fillMaxWidth().padding(16.dp).border(1.dp, color = Color.Black)) {
                MyTextField(modifier = Modifier.width(320.dp),
                    value = description,
                    placeholder = "Описание",
                    onValueChange = {
                        description = it
                    })

            }

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Button(modifier = Modifier.padding(end = 16.dp), onClick = {
                    if (description.isEmpty()) {
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("Заполните поле Описание")
                        }
                    } else {
                        viewModel.saveNews(ScreenModel.NewsItem(
                            name = userInfo.name,
                            surname = userInfo.surname,
                            description = description,
                            date = atTime()
                        ))
                        navController.navigate(BottomNavItem.News.screen_route) {
                            popUpTo(BottomNavItem.News.screen_route){
                                inclusive = true
                            }
                        }
                    }
                }, colors = ButtonDefaults.buttonColors(myColor)) {
                    Text("Опубликовать")
                }
            }
        }

    }
}