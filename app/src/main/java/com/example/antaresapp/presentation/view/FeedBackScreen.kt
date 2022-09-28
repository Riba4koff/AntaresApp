package com.example.antaresapp.presentation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.antaresapp.ui.theme.fontFamilyRoboto
import com.example.antaresapp.R

@Composable
fun FeedBackScreen() {
    Column(
        modifier = Modifier
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

                    }
                ,
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
