package com.example.antaresapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.antaresapp.domain.Login
import com.example.antaresapp.domain.MenuNavigationItems
import com.example.antaresapp.ui.theme.fontFamilyRoboto
import com.example.antaresapp.ui.theme.myColor

//Вход
@Composable
fun LogInScreen(
    navController: NavController,
) {
        val focus = LocalFocusManager.current
    var logIn by remember {
        mutableStateOf("");
    }
    var password by remember {
        mutableStateOf("");
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(top = 128.dp)) {
        Column(
            modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(
                    text = "Log in",
                    fontFamily = fontFamilyRoboto,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Normal
                )
            }
            Spacer(modifier = Modifier.padding(vertical = 16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                TextField(
                    modifier = Modifier.border(BorderStroke(2.dp, Color.Black)),
                    value = logIn,
                    onValueChange = {
                        logIn = it
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        focus.clearFocus()
                    }),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    placeholder = {
                        Text("login")
                    }

                )
            }
            Spacer(modifier = Modifier.padding(vertical = 16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {

                var passwordVisible by rememberSaveable { mutableStateOf(false) }

                TextField(
                    modifier = Modifier.border(BorderStroke(2.dp, Color.Black)),
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Password
                    ),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardActions = KeyboardActions(onDone = {
                        focus.clearFocus()
                    }),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    placeholder = {
                        Text("password")
                    },
                    trailingIcon = {
                        val image = if (passwordVisible)
                            R.drawable.visibility_off
                        else R.drawable.visibility

                        // Please provide localized description for accessibility services
                        val _description = if (passwordVisible) "Hide password" else "Show password"

                        IconButton(onClick = {passwordVisible = !passwordVisible}){
                            Icon(modifier = Modifier.size(24.dp), painter = painterResource(id = image), contentDescription = _description)
                        }
                    }
                )
            }
            Spacer(modifier = Modifier.padding(vertical = 16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Button(onClick = {
                    navController.navigate(MenuNavigationItems.News.screen_route) {
                        popUpTo(Login.LogIn.screen_route) {
                            inclusive = true
                        }
                    }
                }, colors = ButtonDefaults.buttonColors(backgroundColor = myColor)) {
                    Text(
                        text = "Войти",
                        fontFamily = fontFamilyRoboto,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White
                    )
                }
            }
        }
    }

}
