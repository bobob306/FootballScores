package com.benshapiro.footballscores.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.benshapiro.footballscores.data.viewData.HomeCompetitionViewData
import com.benshapiro.footballscores.navigation.Screen

@Composable
fun FSLeagueList(
    leaguesList: List<HomeCompetitionViewData>,
    onSelectLeague: (String) -> Unit,
    onNavigate: (Pair<String, String>) -> Unit,
)
{
    LazyColumn() {
        for (league in leaguesList) {
            item() {
                FSButton(
                    text = league.leagueName,
                    onClick =
                    {
                        val selectedLeague = league.leagueName
                        val selectedLeagueShortCode = league.leagueCode
                        onSelectLeague(selectedLeague)
                        onNavigate(Pair(selectedLeague, selectedLeagueShortCode))
                    },
                    icon = league.leagueIcon,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}