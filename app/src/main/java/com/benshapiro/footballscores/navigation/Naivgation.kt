package com.benshapiro.footballscores.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.benshapiro.footballscores.ui.screen.HomeViewModel
import com.benshapiro.footballscores.ui.screen.homescreen.HomeRoute
import com.benshapiro.footballscores.ui.screen.leaguescreen.LeagueRoute
import com.benshapiro.footballscores.ui.screen.leaguescreen.LeagueViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(Screen.HomeScreen.route) {
            val viewModel = hiltViewModel<HomeViewModel>()
            val homeState by viewModel.uiState.collectAsStateWithLifecycle()
            HomeRoute(homeViewModel = viewModel, navController = navController)
        }
        composable(
            Screen.LeagueScreen.route + "/{League Name}" + "/{League Short Code}",
            arguments = listOf(
                navArgument("League Name") {
                    type = NavType.StringType
                    defaultValue = "Premier League"
                },
                navArgument("League Short Code") {
                    type = NavType.StringType
                    defaultValue = "PL"
                }
            )
        ) {
            val viewModel = hiltViewModel<LeagueViewModel>()
            val leagueState by viewModel.uiState.collectAsStateWithLifecycle()
            val leagueName: String = it.arguments?.getString("League Name") ?: "La Liga"
            val leagueShortCode: String = it.arguments?.getString("League Short Code") ?: "PD"
            LeagueRoute(
                leagueViewModel = viewModel,
                navController = navController,
                leagueName = leagueName,
                leagueShortCode = leagueShortCode,
            )
        }
    }
}


sealed class Screen(val route: String, val arguments: String? = null) {
    object HomeScreen : Screen("home_screen")
    object LeagueScreen : Screen("league_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}