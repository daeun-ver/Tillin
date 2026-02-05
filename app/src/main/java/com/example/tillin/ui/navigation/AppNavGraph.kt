package com.example.tillin.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tillin.ui.screen.home.HomeScreen

@Composable
fun AppNavGraph (navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost (
        navController = navController,
        startDestination = "Home",
        modifier = modifier
    ) {
        composable("splash") {

        }
        composable("Home") {
            HomeScreen(navController)
        }
    }
}