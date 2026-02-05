package com.example.tillin.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.tillin.ui.screen.home.component.HomeTopBar
import com.example.tillin.ui.screen.stats.StatsScreen
import com.example.tillin.ui.screen.til.TilListScreen

enum class HomeTab(val label: String) {
    Home("홈"), STATS("통계")
}

@Composable
fun HomeScreen(navController: NavHostController) {
    var homeSelectedTab by remember { mutableStateOf(HomeTab.Home) }
    Scaffold(topBar = {

    }) { inner ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(inner)
        ) {
            HomeTopBar(
                selected = homeSelectedTab,
                onSelected = { homeSelectedTab = it }
            )

            when (homeSelectedTab) {
                HomeTab.Home -> TilListScreen(onCreate = {navController.navigate("create")})
                HomeTab.STATS -> StatsScreen()
            }
        }
    }
}