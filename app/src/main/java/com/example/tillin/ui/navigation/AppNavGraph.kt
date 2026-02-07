package com.example.tillin.ui.navigation

import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tillin.ui.screen.home.HomeScreen
import com.example.tillin.ui.screen.til.TilCreateScreen
import com.example.tillin.ui.screen.til.TilDetailScreen

@Composable
fun AppNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable("splash") {

        }
        composable(
            route = "home",
            exitTransition = {
                slideOutVertically(targetOffsetY = { it })
            },
            popEnterTransition = {
                slideInVertically(initialOffsetY = { -it })
            }
        ) {
            HomeScreen(navController)
        }

        composable(
            route = "create",
            enterTransition = {
                slideInVertically(initialOffsetY = { it })
            },
            popExitTransition = {
                slideOutVertically(targetOffsetY = { it })
            }
        ) {
            TilCreateScreen(
                onDone = { navController.popBackStack() },
                onBack = { navController.popBackStack() }
            )
        }
        composable("detail") {
            TilDetailScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}