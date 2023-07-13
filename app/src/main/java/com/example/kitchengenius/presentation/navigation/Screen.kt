package com.example.kitchengenius.presentation.navigation

sealed class Screen(val route: String){
    object StartScreen: Screen("start")
    object CookingRecipesScreen: Screen("cookingRecipes")
}