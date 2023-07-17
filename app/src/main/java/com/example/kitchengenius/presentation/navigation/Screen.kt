package com.example.kitchengenius.presentation.navigation

sealed class Screen(val route: String) {
    object SignInScreen : Screen(route = "SignIn_Screen")
    object SignUpScreen : Screen(route = "SignUp_Screen")

    object StartScreen: Screen(route = "Start_Screen")
}