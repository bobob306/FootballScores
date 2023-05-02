package com.benshapiro.footballscores.ui.screen

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
import com.benshapiro.footballscores.data.viewData.HomeCompetitionViewData
import com.benshapiro.footballscores.navigation.Screen
import com.benshapiro.footballscores.ui.components.FSButton
import com.benshapiro.footballscores.ui.components.FSHeader
import com.benshapiro.footballscores.ui.components.FSLeagueList
import com.benshapiro.footballscores.ui.components.FSLoading


@Composable
fun HomeScreen(
    uiState: HomeUiState,
    navController: NavController,
    leaguesList: List<HomeCompetitionViewData>,
    onSelectLeague: (String) -> Unit,
    onNavigate: (Pair<String, String>) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.surface
    ) {
        if (uiState.isLoading) {
            FSLoading()
        } else {
            Column(
                Modifier
                    .padding(start = 8.dp)
            ) {
                FSHeader("Football Scores")
                FSLeagueList(
                    leaguesList = leaguesList,
                    onSelectLeague = onSelectLeague,
                    onNavigate = onNavigate,
                )
                FSButton(
                    text = "Navigate to League Screen",
                    onClick = {
                        navController.navigate(
                            Screen.LeagueScreen.withArgs("Bundesliga", "BL1"),
                        )
                    },
                    icon = null,
                    modifier = Modifier
                )
            }
        }
    }
}