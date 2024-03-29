package com.example.kitchengenius.presentation.screens.recipe_list

import com.example.kitchengenius.domain.model.Recipe
import com.example.kitchengenius.domain.model.User

data class UiState(
    val isLoading: Boolean = false,
    val recipes: List<Recipe> = emptyList(),
    val error: String = "",
    val navigateToRecipeDetail: Recipe? = null,
    val currentUser: User? = null
)
