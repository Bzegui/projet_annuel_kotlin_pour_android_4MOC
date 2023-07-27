package com.example.kitchengenius.domain.repository

import com.example.kitchengenius.domain.model.Recipe
import com.example.kitchengenius.domain.model.User

interface RecipeRepository {
    suspend fun getRecipes(): List<Recipe>
    suspend fun getRecipeById(id: String): Recipe?
    suspend fun  getFiltredRecipes(filter: String): List<Recipe>
    suspend fun getLikedRecipes(idFirebase: String): List<Recipe>
    suspend fun addLikeUser(idFirebase: String, recipeId: String)
}