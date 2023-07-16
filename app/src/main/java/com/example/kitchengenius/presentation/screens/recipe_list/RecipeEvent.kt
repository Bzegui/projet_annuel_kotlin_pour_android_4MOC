package com.example.kitchengenius.presentation.screens.recipe_list

sealed class RecipeEvent{
    object OnButtonClicked : RecipeEvent()
    data class OnTest(val id: String): RecipeEvent()
}