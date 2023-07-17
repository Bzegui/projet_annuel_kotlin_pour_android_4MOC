package com.example.kitchengenius.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.kitchengenius.R

sealed class BottomNavItem(
    val route: String,
    @StringRes val titleResId: Int,
    val icon: ImageVector
) {
    object Home: BottomNavItem(
        route = Screen.StartScreen.route,
        titleResId = R.string.start,
        icon = Icons.Default.Home
    )

    object SignIn : BottomNavItem(
        route = Screen.SignInScreen.route,
        titleResId = R.string.signin,
        icon = Icons.Default.Person
    )
}