package com.example.tillin.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tillin.ui.screen.home.HomeScreen
import com.example.tillin.ui.screen.til.TilCreateScreen
import com.example.tillin.ui.screen.til.TilListScreen

@Composable
fun AppNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "Home",
        modifier = modifier
    ) {
        composable("splash") {

        }
        composable("home") {
            HomeScreen(navController)
        }
        composable("list") {
            TilListScreen(onCreate = { navController.navigate("create") })
        }
        composable("create") {
            TilCreateScreen(onBack = { navController.popBackStack() })
        }
    }
}