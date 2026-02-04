package com.example.tillin.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph (navController: NavHostController, modifier: Modifier) {
    NavHost (
        navController = navController,
        startDestination = "splash",
        modifier = modifier
    ) {
        composable("splash") {

        }
    }
}