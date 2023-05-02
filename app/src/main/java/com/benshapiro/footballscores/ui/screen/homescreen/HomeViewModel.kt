package com.benshapiro.footballscores.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benshapiro.footballscores.R
import com.benshapiro.footballscores.data.ErrorMessage
import com.benshapiro.footballscores.data.viewData.HomeCompetitionViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val viewModelState = MutableStateFlow(HomeViewModelState(isLoading = true))

    val uiState = viewModelState
        .map(HomeViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    private fun refreshLeagues(): List<HomeCompetitionViewData> {
        return listOf(
            HomeCompetitionViewData(
                leagueName = "Premier League",
                leagueIcon = R.drawable.premierleagueicon,
                leagueCode = "PL"
            ),
            HomeCompetitionViewData(
                leagueName = "La Liga",
                leagueIcon = R.drawable.laligaicon,
                leagueCode = "PD"
            ),
            HomeCompetitionViewData(
                leagueName = "Serie A",
                leagueIcon = R.drawable.serieaicon,
                leagueCode = "SA"
            ),
            HomeCompetitionViewData(
                leagueName = "Bundesliga",
                leagueIcon = R.drawable.bundesligaicon,
                leagueCode = "BL1"
            ),
        )
    }

    init {
        viewModelScope.launch {
            viewModelState.update {
                it.copy(
                    leaguesList = refreshLeagues(),
                    isLoading = false
                )
            }
        }
    }

    fun selectLeague(leagueName: String) {
        interactedWithLeagueDetail(leagueName)
    }

    fun interactedWithLeagueDetail(leagueName: String) {
        viewModelState.update {
            it.copy(
                selectedLeague = leagueName,
                isLeagueOpen = true
            )
        }
    }
}

sealed interface HomeUiState {

    val isLoading: Boolean
    val errorMessages: List<ErrorMessage>
    val searchInput: String

    data class NoLeagues(
        override val isLoading: Boolean,
        override val errorMessages: List<ErrorMessage>,
        override val searchInput: String
    ) : HomeUiState

    data class HasLeagues(
        val leaguesList: List<HomeCompetitionViewData>,
        val selectedLeague: String?,
        val isLeagueOpen: Boolean,
        override val isLoading: Boolean,
        override val errorMessages: List<ErrorMessage>,
        override val searchInput: String
    ) : HomeUiState
}

private data class HomeViewModelState(
    val leaguesList: List<HomeCompetitionViewData>? = null,
    val selectedLeague: String? = null,
    val isLeagueOpen: Boolean? = false,
    val isLoading: Boolean = false,
    val errorMessages: List<ErrorMessage> = emptyList(),
    val searchInput: String = "",
) {

    fun toUiState(): HomeUiState =
        if (leaguesList == null) {
            HomeUiState.NoLeagues(
                isLoading = isLoading,
                errorMessages = errorMessages,
                searchInput = searchInput
            )
        } else {
            HomeUiState.HasLeagues(
                leaguesList = leaguesList,
                selectedLeague = selectedLeague,
                isLeagueOpen = isLeagueOpen!!,
                isLoading = isLoading,
                errorMessages = errorMessages,
                searchInput = searchInput,
            )
        }
}

