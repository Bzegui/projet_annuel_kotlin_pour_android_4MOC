package com.example.kitchengenius.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kitchengenius.presentation.screens.login_screen.SignInScreen
import com.example.kitchengenius.presentation.screens.recipe_list.RecipeScreen
import com.example.kitchengenius.presentation.screens.signup_screen.SignUpScreen

@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController(),
) {

    NavHost(
        navController = navController,
        startDestination = Screens.SignInScreen.route
    ) {
        composable(route = Screens.SignInScreen.route) {
            SignInScreen(navController)
        }
        composable(route = Screens.SignUpScreen.route) {
            SignUpScreen(navController)
        }
        composable(route = Screens.RecipeListScren.route){
            RecipeScreen(navController)
        }
    }

}