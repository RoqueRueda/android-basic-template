package com.roque.rueda.basictemplate.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeScreenViewMoldeTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: HomeScreenViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = HomeScreenViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when Search Event event occurs then uiState should be set to Empty`() = runTest {
        viewModel.onEvent(HomeScreenEvent.SearchClosed)
        viewModel.uiState.test {
            assertEquals(HomeScreenUiState.Empty, awaitItem())
        }
    }

    @Test
    fun `when Search Close event occurs is called several times then uiState should be Empty`() = runTest {
        viewModel.onEvent(HomeScreenEvent.SearchClosed)
        viewModel.onEvent(HomeScreenEvent.SearchClosed)
        viewModel.onEvent(HomeScreenEvent.SearchClosed)
        viewModel.uiState.test {
            assertEquals(HomeScreenUiState.Empty, awaitItem())
        }
    }

    @Test
    fun `when Search Expanded event occurs then uiState should be Expanded`() = runTest {
        viewModel.onEvent(HomeScreenEvent.SearchExpanded)
        viewModel.uiState.test {
            // Initial value is Empty
            assertEquals(HomeScreenUiState.Empty, awaitItem())
            // Updated state after event called
            assertEquals(HomeScreenUiState.Expanded, awaitItem())
        }
    }
}
