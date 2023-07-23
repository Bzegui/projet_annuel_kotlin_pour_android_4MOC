package com.example.kitchengenius.domain.repository

import com.example.kitchengenius.domain.model.Recipe

interface RecipeRepository {
    suspend fun getRecipes(): List<Recipe>
    suspend fun getRecipeById(id: String): Recipe?
    suspend fun  getFiltredRecipes(filter: String): List<Recipe>
}