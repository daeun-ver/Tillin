package com.example.tillin.ui.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tillin.ui.screen.home.HomeScreen
import com.example.tillin.ui.screen.til.create.TilCreateScreen
import com.example.tillin.ui.screen.til.detail.TilDetailScreen

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
                when (targetState.destination.route) {
                    "create" -> slideOutVertically(targetOffsetY = { it })
                    "detail" -> slideOutHorizontally(targetOffsetX = { it })
                    else -> null
                }
            },
            popEnterTransition = {
                when(initialState.destination.route) {
                    "create" -> slideInVertically(initialOffsetY = { -it })
                    "detail" -> slideInHorizontally(initialOffsetX = { -it })
                    else -> null
                }
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
        composable("tilDetail/{tilId}") {
            val tilId = it.arguments?.getString("tilId")?.toLong() ?: 0L
            TilDetailScreen(
                tilId = tilId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}