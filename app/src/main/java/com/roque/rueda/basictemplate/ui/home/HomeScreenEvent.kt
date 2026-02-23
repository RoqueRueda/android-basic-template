package com.roque.rueda.basictemplate.ui.home

sealed interface HomeScreenEvent {
    data object SearchClosed : HomeScreenEvent
    data object SearchExpanded : HomeScreenEvent
}
