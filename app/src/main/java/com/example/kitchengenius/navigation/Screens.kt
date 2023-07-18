package com.example.kitchengenius.navigation

sealed class Screens(val route: String) {
    object SignInScreen : Screens(route = "SignIn_Screen")
    object SignUpScreen : Screens(route = "SignUp_Screen")
    object RecipeListScren : Screens(route = "RecipeList_Screen")
    object NewRecipeScreen : Screens(route = "NewRecipe_Screen")

}