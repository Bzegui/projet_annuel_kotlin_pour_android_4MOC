package com.example.kitchengenius.presentation.screens.recipe_detail

import com.example.kitchengenius.domain.model.Recipe
import com.example.kitchengenius.presentation.screens.recipe_list.RecipeEvent

sealed class RecipeDetailEvent {
    object OnLikedRecipe : RecipeDetailEvent()
}