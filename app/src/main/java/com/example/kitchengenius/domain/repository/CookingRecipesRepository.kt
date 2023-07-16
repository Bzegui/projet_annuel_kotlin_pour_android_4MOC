package com.example.kitchengenius.domain.repository

import com.example.kitchengenius.domain.model.cooking_recipes.CookingRecipe

//import androidx.room.Entity

interface CookingRecipesRepository{
    fun getCookingRecipes(): List<CookingRecipe>
}