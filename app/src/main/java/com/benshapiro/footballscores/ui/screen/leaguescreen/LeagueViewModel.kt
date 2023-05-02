package com.benshapiro.footballscores.ui.screen.leaguescreen

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benshapiro.footballscores.data.ErrorMessage
import com.benshapiro.footballscores.data.model.TopScorerDto
import com.benshapiro.footballscores.data.viewData.ScoreViewData
import com.benshapiro.footballscores.data.viewData.TopScorerViewData
import com.benshapiro.footballscores.repository.FootballRepository
import com.benshapiro.footballscores.data.mapper.ViewDataMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeagueViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val footballRepository: FootballRepository,
    private val viewDataMapper: ViewDataMapper,
) : ViewModel() {
    private val viewModelState = MutableStateFlow(LeagueViewModelState(isLoading = true))

    val uiState = viewModelState
        .map(LeagueViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    init {
        viewModelScope.launch {
            viewModelState.update {
                it.copy(isLoading = false)
            }
        }
    }

    fun selectTeam(teamName: String) {
        interactedWithTeamDetail(teamName)
    }

    fun interactedWithTeamDetail(teamName: String) {
        viewModelState.update {
            it.copy(
                selectedTeam = teamName,
                isTeamOpen = true
            )
        }
    }

    fun test(leagueShortCode: String) {
        viewModelScope.launch {
//            footballRepository.getTest()
            if (footballRepository.getTopScorers(leagueShortCode).isSuccessful) {
                onSuccess(topScorerDto = footballRepository.getTopScorers(leagueShortCode).body()!!)
            } else {
                Log.d("Failure", "Oh well then")
            }
        }
    }

    private fun onSuccess(topScorerDto: TopScorerDto) {
        val topScorerViewData = viewDataMapper.mapTopScorerDtoToViewData(topScorerDto)
        viewModelScope.launch {
            viewModelState.update {
                it.copy(
                    topScorers = topScorerViewData
                )
            }
        }
        Log.d("yay", "yay")
    }

}

sealed interface LeagueUiState {

    val isLoading: Boolean
    val errorMessages: List<ErrorMessage>
    val searchInput: String

    data class NoScores(
        override val isLoading: Boolean,
        override val errorMessages: List<ErrorMessage>,
        override val searchInput: String,
    ) : LeagueUiState

    data class HasScores(
        val scoresFeed: List<ScoreViewData>,
        val selectedTeam: String,
        val isTeamOpen: Boolean,
        val favorites: Set<String>,
        override val isLoading: Boolean,
        override val errorMessages: List<ErrorMessage>,
        override val searchInput: String,
        val topScorers: TopScorerViewData?,
    ) : LeagueUiState
}

private data class LeagueViewModelState(
    val scoresFeed: List<ScoreViewData>? = null,
    val selectedTeam: String? = null,
    val isTeamOpen: Boolean = false,
    val favorites: Set<String> = emptySet(),
    val isLoading: Boolean = false,
    val errorMessages: List<ErrorMessage> = emptyList(),
    val searchInput: String = "",
    val topScorers: TopScorerViewData? = null
) {
    fun toUiState(): LeagueUiState =
        if (scoresFeed == null) {
            LeagueUiState.NoScores(
                isLoading = isLoading,
                errorMessages = errorMessages,
                searchInput = searchInput
            )
        } else {
            LeagueUiState.HasScores(
                scoresFeed = scoresFeed,
                selectedTeam = selectedTeam!!,
                /*scoresFeed.allScores.find {
                    it.id == selectedScoreId
                },

                 */
                isTeamOpen = isTeamOpen,
                favorites = favorites,
                isLoading = isLoading,
                errorMessages = errorMessages,
                searchInput = searchInput,
                topScorers = topScorers,
            )
        }
}