package com.benshapiro.footballscores.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.benshapiro.footballscores.screen.HomeScreen
import com.benshapiro.footballscores.screen.HomeViewModel
import com.benshapiro.footballscores.screen.HomeUiState

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home_screen"
    ){
        composable(Screen.HomeScreen.route){
            val viewModel = viewModel<HomeViewModel>()
            val homeState by viewModel.uiState.collectAsStateWithLifecycle()
            HomeScreen(uiState = homeState as HomeUiState)
        }
    }
}


sealed class Screen (val route: String, val arguments: String? = null) {
    object HomeScreen : Screen("home_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}