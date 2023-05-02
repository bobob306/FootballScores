package com.benshapiro.footballscores.ui.screen.homescreen

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.benshapiro.footballscores.navigation.Screen
import com.benshapiro.footballscores.ui.screen.HomeScreen
import com.benshapiro.footballscores.ui.screen.HomeUiState
import com.benshapiro.footballscores.ui.screen.HomeViewModel

@Composable
fun HomeRoute(
    navController: NavController,
    homeViewModel: HomeViewModel,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
) {
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    HomeRoute(
        homeViewModel = homeViewModel,
        navController = navController,
        uiState = uiState,
        snackbarHostState = snackbarHostState,
        onSelectLeague = {homeViewModel.selectLeague(it)},
        onNavigate = { navController.navigate(Screen.LeagueScreen.withArgs(it.first, it.second)) },
    )
}

@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel,
    navController: NavController,
    uiState: HomeUiState,
    snackbarHostState: SnackbarHostState,
    onSelectLeague: (String) -> Unit,
    onNavigate: (Pair<String, String>) -> Unit,
) {
    val homeScreenHasLeagues = when (uiState) {
        is HomeUiState.HasLeagues -> uiState.leaguesList
        else -> emptyList()
    }

    HomeScreen(
        uiState = uiState,
        navController = navController,
        leaguesList = homeScreenHasLeagues,
        onSelectLeague = {homeViewModel.selectLeague(it)},
        snackbarHostState = snackbarHostState,
        onNavigate = onNavigate,
    )
}