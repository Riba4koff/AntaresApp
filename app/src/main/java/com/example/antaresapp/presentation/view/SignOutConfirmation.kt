package com.example.antaresapp.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.antaresapp.domain.Login
import com.example.antaresapp.domain.MenuNavigationItems
import com.example.antaresapp.ui.theme.myColor

@Composable
fun SignOutConfirmation(
    navController: NavController
) {
    val openDialog = remember { mutableStateOf(true) }
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = { Text(text = "Подтверждение действия") },
            text = { Text("Вы действительно хотите выйти из аккаунта?") },
            buttons = {
                Row(Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp, bottom = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Button(colors = ButtonDefaults.buttonColors(myColor),
                        onClick = {
                            openDialog.value = false
                            navController.navigate(Login.LogIn.screen_route) {
                                //Очищаем стек активити
                                popUpTo(MenuNavigationItems.News.screen_route) {
                                    //Включительно до новостей
                                    inclusive = true
                                }
                            }
                        }) {
                        Text("Выйти")
                    }
                    Button(colors = ButtonDefaults.buttonColors(myColor),
                        onClick = {
                            openDialog.value = false
                            navController.navigate(MenuNavigationItems.News.screen_route){
                                popUpTo(MenuNavigationItems.News.screen_route){
                                    inclusive = true
                                }
                            }
                        }) {
                        Text("Отменить")
                    }
                }
            }
        )
    }
}