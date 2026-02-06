package com.example.tillin.ui.navigation

import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tillin.ui.screen.home.HomeScreen
import com.example.tillin.ui.screen.stats.month.MonthTab
import com.example.tillin.ui.screen.stats.week.WeekTab
import com.example.tillin.ui.screen.til.TilCreateScreen
import com.example.tillin.ui.screen.til.TilDetailScreen
import com.example.tillin.ui.screen.til.TilListScreen

@Composable
fun AppNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable("splash") {

        }
        composable("home") {
            HomeScreen(navController)
        }

        composable(
            route = "create",
            enterTransition = {
                slideInVertically(initialOffsetY = { it })
            },
            exitTransition = {
                slideOutVertically(targetOffsetY = { it })
            }
        ) {
            TilCreateScreen(onBack = { navController.popBackStack() })
        }
        composable("detail") {
            TilDetailScreen()
        }
    }
}