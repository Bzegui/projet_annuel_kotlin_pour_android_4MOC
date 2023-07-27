package com.example.kitchengenius.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kitchengenius.domain.model.Recipe
import com.example.kitchengenius.presentation.screens.login_screen.SignInScreen
import com.example.kitchengenius.presentation.screens.recipe_detail.RecipeDetailScreen
import com.example.kitchengenius.presentation.screens.recipe_detail.RecipeDetailViewModel
import com.example.kitchengenius.presentation.screens.recipe_list.RecipeScreen
import com.example.kitchengenius.presentation.screens.recipe_list.RecipeViewModel
import com.example.kitchengenius.presentation.screens.signup_screen.SignUpScreen
import com.squareup.moshi.Moshi
import dagger.hilt.android.lifecycle.HiltViewModel

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

        composable(route = Screens.RecipeListScreen.route){
            RecipeScreen(navController = navController)
        }

        composable(
            Screens.RecipeDetailScreen.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) {
            RecipeDetailScreen(navController = navController)
        }
    }

}