package com.example.antaresapp.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.antaresapp.domain.models.ScreenModel
import com.example.antaresapp.domain.models.UserInfo
import com.example.antaresapp.domain.models.UserRights
import com.example.antaresapp.presentation.viewModels.viewModels.ScreenModelsViewModel
import com.example.antaresapp.ui.theme.fontFamilyRoboto

//Вид новости в новостной ленте
@Composable
fun CardNewsFeed(
    modifier: Modifier = Modifier,
    newsItem: ScreenModel.NewsItem,
    user: UserInfo,
    viewModel: ScreenModelsViewModel,
) {

    var expanded by remember { mutableStateOf(false) }

    Card(modifier = modifier
        .fillMaxWidth()
        .padding(8.dp), elevation = 5.dp) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Icon(painter = painterResource(id = com.example.antaresapp.R.drawable.profile2),
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
        if (user.rights == UserRights.User.right)
        else if (user.rights == UserRights.Admin.right) {
            Box(modifier = Modifier.padding(end = 8.dp, start = 326.dp)) {
                Row(Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                    horizontalArrangement = Arrangement.End) {
                    IconButton(onClick = { expanded = true }) {
                        Icon(modifier = Modifier.size(24.dp),
                            painter = painterResource(id = com.example.antaresapp.R.drawable.more_vert),
                            contentDescription = "")
                    }
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }) {
                    Text(text = "Удалить новость", modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                            //Удалить новость
                            viewModel.deleteNews(newsItem)
                            expanded = false

                        }
                    )
                }
            }
        }
    }

}
