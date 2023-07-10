package com.example.kitchengenius.domain.model.cooking_recipes

data class CookingRecipe(
    val recipeName: String,
    val recipeDescription: String,
    val recipeQuantityFor: String,
    val recipeCookingTime: Int,
)
