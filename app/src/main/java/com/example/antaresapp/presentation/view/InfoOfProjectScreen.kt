package com.example.antaresapp.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.antaresapp.R
import com.example.antaresapp.presentation.Navigation.MenuNavigationItems
import com.example.antaresapp.presentation.viewModelimport.MainViewModel
import com.example.antaresapp.ui.theme.fontFamilyRoboto

@Composable
fun InfoOfProjectScreen(
    modifier: Modifier = Modifier,
    navController: NavController) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            IconButton(onClick = { navController.navigate(MenuNavigationItems.Projects.screen_route) }) {
                Icon(painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = "back",
                    modifier = Modifier.size(24.dp))
            }
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Card(modifier = Modifier.fillMaxWidth(),
                elevation = 5.dp,
                shape = RoundedCornerShape(15.dp)) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Name Project",
                        fontFamily = fontFamilyRoboto,
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp
                    )
                }
            }
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Card(modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(end = 8.dp),
                elevation = 5.dp,
                shape = RoundedCornerShape(15.dp)) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Количество\nучастников",
                        fontFamily = fontFamilyRoboto,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = "6",
                        fontFamily = fontFamilyRoboto,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            }
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp),
                elevation = 5.dp,
                shape = RoundedCornerShape(15.dp)) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Сроки проекта",
                        fontFamily = fontFamilyRoboto,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    Text(
                        text = "20.12.2003-20.12.2003",
                        fontFamily = fontFamilyRoboto,
                        fontWeight = FontWeight.Normal,
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                }
            }
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Card(modifier = Modifier.fillMaxWidth(),
                elevation = 5.dp,
                shape = RoundedCornerShape(15.dp)) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Описание проекта",
                        fontFamily = fontFamilyRoboto,
                        fontWeight = FontWeight.Bold,
                        fontSize = 26.sp
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = "skdfhadsofadsoigfasdogasdoigsaogsdoigasdogsaigasdigjasdigjsagjsligjsaoigdjsogjasogijasogdisgo;iasjgoisadjg",
                        fontFamily = fontFamilyRoboto,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.padding(4.dp))

                }
            }
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Card(modifier = Modifier.fillMaxWidth(),
                elevation = 5.dp,
                shape = RoundedCornerShape(15.dp)) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Цель проекта",
                        fontFamily = fontFamilyRoboto,
                        fontWeight = FontWeight.Bold,
                        fontSize = 26.sp
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = "Получить какую-то выгоду",
                        fontFamily = fontFamilyRoboto,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.padding(4.dp))

                }
            }
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Card(modifier = Modifier.fillMaxWidth(),
                elevation = 5.dp,
                shape = RoundedCornerShape(15.dp)) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Прогресс проекта",
                        fontFamily = fontFamilyRoboto,
                        fontWeight = FontWeight.Bold,
                        fontSize = 26.sp
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = "В процессе",
                        fontFamily = fontFamilyRoboto,
                        fontWeight = FontWeight.Normal,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.padding(4.dp))

                }
            }
        }
    }
}
