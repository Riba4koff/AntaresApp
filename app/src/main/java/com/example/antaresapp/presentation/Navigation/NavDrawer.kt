package com.example.antaresapp.presentation.Navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavController
import com.example.antaresapp.domain.MenuNavigationItems
import com.example.antaresapp.ui.theme.fontFamilyRoboto
import com.example.antaresapp.ui.theme.myColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(myColor),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(modifier = Modifier.padding(start = 24.dp),text = "Antares App", fontSize = 24.sp)
    }
}

@Composable
fun DrawerBody(
    listOfMenuItems: List<MenuNavigationItems>,
    openDialog : () -> Unit = {},
    navController : NavController,
    scope : CoroutineScope,
    closeDrawer : () -> Unit = {}
) {
    LazyColumn{
        items(listOfMenuItems){ item ->
            if (item.screen_route == MenuNavigationItems.Exit.screen_route){
                openDialog()
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = {
                    scope.launch {
                        closeDrawer()
                    }
                    navController.navigate(item.screen_route) {
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = false
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }), verticalAlignment = Alignment.CenterVertically) {
                Row(Modifier.fillMaxWidth().padding(16.dp)){
                    Icon(modifier = Modifier.size(30.dp),
                        painter = painterResource(id = item.icon),
                        contentDescription = item.description)
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = item.title,
                        fontFamily = fontFamilyRoboto,
                        fontSize = 18.sp
                    )
                }
            }
            Divider(modifier = Modifier.padding(end = 48.dp),
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f),
                thickness = 1.dp)
        }
    }

}