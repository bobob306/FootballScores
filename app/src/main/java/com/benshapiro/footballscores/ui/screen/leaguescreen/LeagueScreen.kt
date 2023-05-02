package com.benshapiro.footballscores.ui.screen.leaguescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.benshapiro.footballscores.navigation.Screen
import com.benshapiro.footballscores.ui.components.FSButton
import com.benshapiro.footballscores.ui.components.FSHeader
import com.benshapiro.footballscores.ui.components.FSLoading

@Composable
fun LeagueScreen(
    uiState: LeagueUiState,
    navController: NavController,
    onSelectTeam: (String) -> Unit,
    snackbarHostState: SnackbarHostState,
    leagueName: String,
    leagueShortCode: String,
    onTest: (String) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.surface
    ) {
        if (uiState.isLoading) FSLoading() else {
            Column(Modifier.padding(start = 8.dp)) {
                FSHeader(leagueName)
                FSButton(
                    text = "Navigate to Home Screen",
                    onClick = { navController.navigate(Screen.HomeScreen.route) },
                    icon = null,
                    modifier = Modifier
                )
                FSButton(
                    text = "Test response",
                    onClick = { onTest(leagueShortCode) },
                    icon = null,
                    modifier = Modifier
                )
            }
        }
    }
}