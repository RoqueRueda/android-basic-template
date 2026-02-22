package com.roque.rueda.basictemplate.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.roque.rueda.basictemplate.R

enum class AppDestinations(
    val label: String,
    val icon: @Composable () -> ImageVector,
) {
    HOME(label = "Home", icon = { ImageVector.Companion.vectorResource(R.drawable.ic_home) }),
    FAVORITES(label = "Favorites", icon = { ImageVector.Companion.vectorResource(R.drawable.ic_favorite) }),
    PROFILE(label = "Profile", icon = { ImageVector.Companion.vectorResource(R.drawable.ic_account_box) }),
}
