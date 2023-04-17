package com.benshapiro.footballscores.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benshapiro.footballscores.data.ErrorMessage
import com.benshapiro.footballscores.data.model.ScoreDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val viewModelState = MutableStateFlow(HomeViewModelState(isLoading = true))

    val uiState = viewModelState
        .map (HomeViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )
}

sealed interface HomeUiState {

    val isLoading: Boolean
    val errorMessages: List<ErrorMessage>
    val searchInput: String

    data class NoScores(
        override val isLoading: Boolean,
        override val errorMessages: List<ErrorMessage>,
        override val searchInput: String
    ) : HomeUiState

    data class HasScores(
        val scoresFeed: List<ScoreDto>,
        val selectedScore: String,
        val isScoreOpen: Boolean,
        val favorites: Set<String>,
        override val isLoading: Boolean,
        override val errorMessages: List<ErrorMessage>,
        override val searchInput: String
    ) : HomeUiState
}

private data class HomeViewModelState(
    val scoresFeed: List<ScoreDto>? = null,
    val selectedScoreId: String? = null,
    val isScoreOpen: Boolean = false,
    val favorites: Set<String> = emptySet(),
    val isLoading: Boolean = false,
    val errorMessages: List<ErrorMessage> = emptyList(),
    val searchInput: String = "",
) {

    fun toUiState(): HomeUiState =
        if (scoresFeed == null) {
            HomeUiState.NoScores(
                isLoading = isLoading,
                errorMessages = errorMessages,
                searchInput = searchInput
            )
        } else {
            HomeUiState.HasScores(
                scoresFeed = scoresFeed,
                selectedScore = selectedScoreId!!,
                /*scoresFeed.allScores.find {
                    it.id == selectedScoreId
                },

                 */
                isScoreOpen = isScoreOpen,
                favorites = favorites,
                isLoading = isLoading,
                errorMessages = errorMessages,
                searchInput = searchInput,
            )
        }
}

