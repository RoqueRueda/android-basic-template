package com.roque.rueda.basictemplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.lifecycle.viewmodel.compose.viewModel
import com.roque.rueda.basictemplate.ui.home.HomeScreen
import com.roque.rueda.basictemplate.ui.home.HomeScreenViewModel
import com.roque.rueda.basictemplate.ui.navigation.AppDestinations
import com.roque.rueda.basictemplate.ui.theme.BasicTemplateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicTemplateTheme {
                BasicTemplateApp()
            }
        }
    }
}

@Composable
fun BasicTemplateApp() {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }
    val viewModel: HomeScreenViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            imageVector = it.icon(),
                            contentDescription = it.label,
                        )
                    },
                    label = { Text(it.label) },
                    selected = it == currentDestination,
                    onClick = { currentDestination = it },
                )
            }
        },
    ) {
        HomeScreen(
            uiState = uiState,
            onEvent = viewModel::onEvent,
        )
    }
}

@PreviewScreenSizes
@Composable
private fun EmptyAppPreview() {
    BasicTemplateTheme {
        BasicTemplateApp()
    }
}
