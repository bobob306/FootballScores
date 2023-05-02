package com.benshapiro.footballscores.ui.screen.leaguescreen

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController

@Composable
fun LeagueRoute(
    navController: NavController,
    leagueViewModel: LeagueViewModel,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    leagueName: String,
    leagueShortCode: String,
) {
    val uiState by leagueViewModel.uiState.collectAsStateWithLifecycle()

    LeagueRoute(
        navController = navController,
        leagueViewModel = leagueViewModel,
        uiState = uiState,
        snackbarHostState = snackbarHostState,
        onSelectTeam = {leagueViewModel.selectTeam(it)},
        leagueName = leagueName,
        leagueShortCode = leagueShortCode,
        onTest = {leagueViewModel.test(leagueShortCode)}
    )
}

@Composable
fun LeagueRoute(
    navController: NavController,
    leagueViewModel: LeagueViewModel,
    uiState: LeagueUiState,
    snackbarHostState: SnackbarHostState,
    onSelectTeam: (String) -> Unit,
    leagueName: String,
    leagueShortCode: String,
    onTest: (String) -> Unit,
) {

    LeagueScreen(
        uiState = uiState,
        navController = navController,
        onSelectTeam = { leagueViewModel.selectTeam(it) },
        snackbarHostState,
        leagueName = leagueName,
        leagueShortCode = leagueShortCode,
        onTest = { onTest(leagueShortCode) },
    )
}