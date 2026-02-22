package com.roque.rueda.basictemplate.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performKeyInput
import androidx.compose.ui.test.performTextInput
import com.roque.rueda.basictemplate.MainActivity
import com.roque.rueda.basictemplate.R
import com.roque.rueda.basictemplate.ui.home.HomeScreen
import com.roque.rueda.basictemplate.ui.home.HomeScreenUiState
import com.roque.rueda.basictemplate.ui.theme.BasicTemplateTheme
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun `when app is launched search bar should be displayed`() {
        val activity = composeTestRule.activity
        composeTestRule.onNodeWithText(activity.getString(R.string.search_city)).assertIsDisplayed()
    }

    @Test
    fun `when search is typed then city suggestions should be displayed`() {
        val activity = composeTestRule.activity
        val searchBar = composeTestRule.onNodeWithText(activity.getString(R.string.search_city))
        searchBar.assertIsDisplayed()
        searchBar.performClick()
        searchBar.performTextInput("Zapop")
        composeTestRule.onNodeWithText("Zapopan").assertIsDisplayed()
    }
}
