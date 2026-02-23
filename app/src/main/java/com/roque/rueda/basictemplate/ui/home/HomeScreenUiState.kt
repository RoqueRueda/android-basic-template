package com.roque.rueda.basictemplate.ui.home

import kotlinx.collections.immutable.ImmutableList

sealed class HomeScreenUiState {
    data object Empty : HomeScreenUiState()
    object Expanded : HomeScreenUiState()
    data class SearchResults(val results: ImmutableList<String>): HomeScreenUiState()
}
