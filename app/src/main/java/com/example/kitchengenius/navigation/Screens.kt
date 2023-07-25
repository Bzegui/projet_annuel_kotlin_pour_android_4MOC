package com.example.kitchengenius.navigation

sealed class Screens(val route: String) {
    object SignInScreen : Screens(route = "SignIn_Screen")
    object SignUpScreen : Screens(route = "SignUp_Screen")
    object RecipeListScreen : Screens(route = "RecipeList_Screen")
    object RecipeDetailScreen: Screens(route = "RecipeDetail_Screen/{id}")
    object ModifieRecipeScreen: Screens(route = "ModifieRecipeScreen/{id}")
}