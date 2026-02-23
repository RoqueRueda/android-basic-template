package com.roque.rueda.basictemplate.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class HomeScreenViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<HomeScreenUiState>(HomeScreenUiState.Empty)
    val uiState: StateFlow<HomeScreenUiState> = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(500L),
        initialValue = HomeScreenUiState.Empty
    )

    /**
     * Called when the search is expanded
     * @param event User interaction with home screen
     */
    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            HomeScreenEvent.SearchClosed -> {
                searchClosed()
            }
            HomeScreenEvent.SearchExpanded -> {
                searchExpanded()
            }
        }
    }

    private fun searchClosed() {
        _uiState.update { HomeScreenUiState.Empty }
    }

    private fun searchExpanded() {
        _uiState.update { HomeScreenUiState.Expanded }
    }
}
