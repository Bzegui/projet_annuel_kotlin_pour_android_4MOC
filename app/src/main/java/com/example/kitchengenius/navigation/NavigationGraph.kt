package com.example.kitchengenius.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kitchengenius.domain.model.Recipe
import com.example.kitchengenius.presentation.screens.edit_recipe_screen.EditRecipeScreen
import com.example.kitchengenius.presentation.screens.login_screen.SignInScreen
import com.example.kitchengenius.presentation.screens.recipe_detail.RecipeDetailScreen
import com.example.kitchengenius.presentation.screens.recipe_detail.RecipeDetailViewModel
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

        composable(route = Screens.RecipeListScreen.route){
            RecipeScreen(navController = navController)
        }

        composable(
            Screens.RecipeDetailScreen.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) {
            RecipeDetailScreen(navController = navController)
        }

        composable(
            route = Screens.EditRecipeScreen.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) {
            EditRecipeScreen(navController = navController)
        }
    }

}