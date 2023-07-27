package com.example.kitchengenius.presentation.screens.recipe_list

import com.example.kitchengenius.domain.model.Recipe

sealed class RecipeEvent{
    object OnButtonClicked : RecipeEvent()
    data class OnTest(val id: String): RecipeEvent()
    data class OnRecipeClicked(val recipe: Recipe) : RecipeEvent()
}