package com.roque.rueda.basictemplate.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.roque.rueda.basictemplate.R
import com.roque.rueda.basictemplate.ui.theme.BasicTemplateTheme
import com.roque.rueda.basictemplate.ui.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    uiState: HomeScreenUiState,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        val query = rememberTextFieldState()
        var expanded by rememberSaveable { mutableStateOf(false) }

        SearchBar(
            modifier =
                Modifier
                    .align(Alignment.TopCenter)
                    .then(
                        if (expanded) {
                            Modifier
                        } else {
                            Modifier.padding(horizontal = Dimens.PageMargin)
                        }
                    )
                    .semantics { traversalIndex = 0f },
            inputField = {
                SearchBarDefaults.InputField(
                    query = query.text.toString(),
                    onQueryChange = {
                        query.edit {
                            replace(
                                start = 0,
                                end = length,
                                text = it,
                            )
                        }
                    },
                    onSearch = {
                        expanded = false
                    },
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    placeholder = { Text(stringResource(R.string.search_city)) },
                )
            },
            expanded = expanded,
            onExpandedChange = { expanded = it },
        ) {
            if (uiState is HomeScreenUiState.SearchResults && uiState.results.isNotEmpty()) {
                Column(Modifier.verticalScroll(rememberScrollState())) {
                    uiState.results.forEach { result ->
                        ListItem(
                            headlineContent = { Text(result) },
                            modifier =
                                Modifier
                                    .clickable {
                                        query.edit {
                                            replace(
                                                start = 0,
                                                end = length,
                                                text = result,
                                            )
                                        }
                                        expanded = false
                                    }.fillMaxWidth(),
                        )
                    }
                }
            }
        }
    }
}

@Composable
@PreviewLightDark
private fun EmptyHomeScreenPreview() {
    BasicTemplateTheme {
        HomeScreen(uiState = HomeScreenUiState.Empty)
    }
}
