package com.example.kitchengenius.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kitchengenius.presentation.CookingRecipesScreen
import com.example.kitchengenius.presentation.StartScreen
import com.example.kitchengenius.view_models.CookingRecipesViewModel

/* navigation bloc */
@Composable
fun KitchenGeniusNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: CookingRecipesViewModel = viewModel(),
    startDestination: String = Screen.StartScreen.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.StartScreen.route) {
            StartScreen(onNavigateToCookingRecipesList = {
                navController.navigate(Screen.CookingRecipesScreen.route)},
                cookingRecipesViewModel = viewModel
            )
        }
        composable("cookingRecipes") {
            CookingRecipesScreen(cookingRecipesViewModel = viewModel)
        }
    }
}
