package com.example.antaresapp

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.antaresapp.data.RepositoryImpl.FinanceRepositoryImpl
import com.example.antaresapp.data.storage.FinanceStorage.FinanceStorageImpl
import com.example.antaresapp.domain.models.FinanceModel
import com.example.antaresapp.domain.useCase.FinanceUseCase.GetListFinanceOperationsUseCase
import com.example.antaresapp.domain.useCase.ProjectsUseCase.SaveFinanceOperationUseCase
import com.example.antaresapp.presentation.Navigation.DrawerNavigation
import com.example.antaresapp.ui.theme.fontFamilyRoboto
import com.example.antaresapp.ui.theme.myColor
import kotlinx.coroutines.launch

private val financeStorage = FinanceStorageImpl()
private val financeRepository = FinanceRepositoryImpl(financeStorage)
private val SaveFinanceOperationUseCase = SaveFinanceOperationUseCase(financeRepository)
private val GetListFinanceOperationsUseCase = GetListFinanceOperationsUseCase(financeRepository)


@Composable
fun FinanceScreen(scaffoldState: ScaffoldState, navController: NavController) {
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text("Финансы")
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
        BodyFinanceScreen(modifier = Modifier.padding(paddingValues = it))
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun BodyFinanceScreen(modifier: Modifier) {
    val financeList = GetListFinanceOperationsUseCase.execute()

    var balance by remember { mutableStateOf(0) }
    val scope = rememberCoroutineScope()
    var index by remember { mutableStateOf(0) }
    scope.launch {
        while (index != financeList.size) {
            balance += financeList[index].sum!!
            index++
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 64.dp)

        ) {
            item {
                HeaderFinanceScreen()
            }
            itemsIndexed(
                financeList
            ) { index, item ->
                CardProfit(DataFinanceScreen = item)
            }
        }
        Balance(balance = balance)
    }
}

@Composable
fun Balance(balance: Int) {
    Box(
        modifier = Modifier
            .padding(start = 180.dp, top = 500.dp)
            .size(width = 200.dp, height = 100.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, top = 64.dp), elevation = 5.dp
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = "Баланс: $balance"
                )
            }
        }
    }
}

@Composable
fun HeaderFinanceScreen() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(100.dp)
                    .padding(8.dp),
                elevation = 4.dp
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Операция",
                        modifier = Modifier,
                        fontFamily = fontFamilyRoboto,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 22.sp
                    )
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(8.dp),
                elevation = 4.dp
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Прибыль",
                        modifier = Modifier,
                        fontFamily = fontFamilyRoboto,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 22.sp
                    )
                }
            }
        }
    }
}

@Composable
fun CardProfit(DataFinanceScreen: FinanceModel) {

    Box(modifier = Modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            elevation = 4.dp
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(8.dp)
                ) {
                    Text(
                        "${DataFinanceScreen.operation}",
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .padding(start = 20.dp)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                ) {
                    Text(
                        "+${DataFinanceScreen.sum}p",
                        modifier = Modifier
                            .padding(horizontal = 45.dp)
                            .padding(start = 20.dp)
                    )
                }
            }
        }
    }
}
