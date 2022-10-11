package com.example.antaresapp.presentation.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.ui.Alignment
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

//Вид опроса в новостной ленте
@Composable
fun CardSurvey(
    surveyInfo: ScreenModel.SurveyInfo,
    viewModel: ScreenModelsViewModel,
    user: UserInfo,
    modifier: Modifier = Modifier,
) {

    val (selectedOption, onOptionSelected) = remember { mutableStateOf(surveyInfo.listOptionItem[0]) }
    var chosenItem by remember { mutableStateOf(-1) }
    var allow_click = true
    var showRadioButton = true
    var indexRemoved: Int = 0
    var refreshCountVotes by remember { mutableStateOf(0) }

    Card(modifier = modifier
        .fillMaxWidth()
        .padding(8.dp), elevation = 5.dp) {

        Box(modifier = Modifier.padding(top = 8.dp, start = 326.dp)) {
            if (user.rights == UserRights.User.right) { /* BUTTON UNAVAILABLE */
            }
            if (user.rights == UserRights.Admin.right) {
                var expanded by remember { mutableStateOf(false) }
                IconButton(onClick = { expanded = true }) {
                    Icon(
                        modifier = Modifier.size(28.dp),
                        painter = painterResource(id = com.example.antaresapp.R.drawable.more_vert),
                        contentDescription = "Дополнительно")
                }
                //Кнопка отменить голос
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }) {
                    Text(
                        text = "Отменить голос",
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable {
                                if (!allow_click) {
                                    //Разрешает нажимать на "применить"
                                    allow_click = true
                                    //Показывает RadioButton
                                    showRadioButton = true
                                    //Удаляет элемент
                                    viewModel.deleteVotes(indexRemoved, surveyInfo)
                                    //Находит новые проценты
                                    viewModel.findPercent(surveyInfo.listOptionItem, surveyInfo)
                                    expanded = false
                                    refreshCountVotes = surveyInfo.votes
                                }
                            }
                    )
                    Text(text = "Удалить опрос", modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                            viewModel.deleteSurvey(surveyInfo)
                            expanded = false
                        })
                }
            }
        }

        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
                Icon(painter = painterResource(id = com.example.antaresapp.R.drawable.profile2),
                    contentDescription = "Icon",
                    modifier = Modifier.size(64.dp))
                Column(Modifier.fillMaxWidth()) {
                    Text(text = "${surveyInfo.name} ${surveyInfo.surname}",
                        fontSize = 16.sp,
                        fontFamily = fontFamilyRoboto,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 16.dp, bottom = 2.dp))

                    Text(text = "${surveyInfo.date}",
                        fontSize = 10.sp,
                        fontFamily = fontFamilyRoboto,
                        fontWeight = FontWeight.Thin,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 16.dp))
                }
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)) {
                Text(text = "${surveyInfo.description}",
                    fontSize = 12.sp,
                    fontFamily = fontFamilyRoboto,
                    fontWeight = FontWeight.Light,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            Divider()

            //Название опроса
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
                horizontalArrangement = Arrangement.Center) {
                Text(text = "${surveyInfo.nameSurvey}",
                    fontFamily = fontFamilyRoboto,
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp)
            }

            Divider(Modifier.padding(top = 8.dp, start = 64.dp, end = 64.dp, bottom = 4.dp))

            //Имя фамилия того, кто задал опрос
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(text = "${surveyInfo.name} ${surveyInfo.surname}",
                    fontFamily = fontFamilyRoboto,
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp)
            }

            Spacer(Modifier.padding(top = 8.dp))

            //Список с вариантами
            Column(Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)) {
                for (item in surveyInfo.listOptionItem) {
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp, bottom = 4.dp),
                        elevation = 2.dp,
                        shape = RoundedCornerShape(15.dp)) {

                        Row(modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically) {
                            Row(modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(0.9f)
                                        .padding(8.dp)
                                        .selectable(
                                            selected = (item == selectedOption),
                                            onClick = {
                                                if (allow_click) {
                                                    chosenItem = viewModel.getIndex(item,
                                                        surveyInfo.listOptionItem)
                                                    onOptionSelected(item)
                                                }
                                            },
                                        ),
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    AnimatedVisibility(visible = showRadioButton) {
                                        RadioButton(
                                            selected = (viewModel.getIndex(item,
                                                surveyInfo.listOptionItem) == chosenItem),
                                            onClick = null
                                        )
                                    }
                                    Text(text = item.option,
                                        modifier = Modifier.padding(4.dp))
                                    Text(text = ":    " + viewModel.getPercent(surveyInfo.listOptionItem,
                                        viewModel.getIndex(item, surveyInfo.listOptionItem)).toInt()
                                        .toString() + "%")
                                    //Spacer(modifier = Modifier.padding(start = 160.dp))
                                }
                                AnimatedVisibility(visible = (viewModel.getIndex(item,
                                    surveyInfo.listOptionItem) == chosenItem),
                                    exit = fadeOut(animationSpec = tween(10)),
                                    enter = fadeIn(animationSpec = tween(10))
                                ) {
                                    IconButton(modifier = Modifier
                                        .padding(end = 16.dp)
                                        .size(20.dp), onClick = {

                                        //При нажатии расчитывается процентаж и блокируются другие варианты,
                                        //Потом добавлю "Отмена голоса"
                                        //Блокировка делается с помощью allow_click, изначально равен true
                                        //После нажатия на выбранный элемент и нажатии на галочку
                                        //allow_click = false -> выбор больше невозможен
                                        allow_click = false

                                        //Убираем левую кнопку
                                        showRadioButton = false

                                        //Добавляем при нажатии количество проголосовавших
                                        viewModel.addVotes(viewModel.getIndex(item,
                                            surveyInfo.listOptionItem), surveyInfo)
                                        indexRemoved =
                                            viewModel.getIndex(item, surveyInfo.listOptionItem)

                                        //Поиск процентов
                                        viewModel.findPercent(surveyInfo.listOptionItem,
                                            surveyInfo)

                                        refreshCountVotes = surveyInfo.votes

                                        //выбранный элемент равен -1 и не равен текущему item
                                        //Что позволяет скрыть IconButton
                                        chosenItem = -1

                                    }) {
                                        Icon(modifier = Modifier.size(20.dp),
                                            painter = painterResource(id = com.example.antaresapp.R.drawable.done),
                                            contentDescription = "done")
                                    }

                                }
                            }
                        }
                    }
                }
            }
            //Количество участников
            Spacer(Modifier.padding(top = 8.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp), horizontalArrangement = Arrangement.Center) {
                AnimatedVisibility(visible = (surveyInfo.votes == refreshCountVotes)) {
                    Text(text = "Количество голосов: ${surveyInfo.votes}",
                        fontFamily = fontFamilyRoboto,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal)
                }
            }
        }
    }
}
