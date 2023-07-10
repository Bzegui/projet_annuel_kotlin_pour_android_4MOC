package com.example.kitchengenius.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kitchengenius.view_models.CookingRecipesViewModel
import com.example.kitchengenius.views.CookingRecipesList
import com.example.kitchengenius.views.StartScreen

/* navigation bloc */
@Composable
fun KitchenGeniusNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: CookingRecipesViewModel = viewModel(),
    startDestination: String = "startScreen"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("startScreen") {
            StartScreen(onNavigateToCookingRecipesList = {
                navController.navigate("cookingRecipesList")},
                cookingRecipesViewModel = viewModel
            )
        }
        composable("cookingRecipesList") {
            CookingRecipesList(cookingRecipesViewModel = viewModel)
        }
    }
}
