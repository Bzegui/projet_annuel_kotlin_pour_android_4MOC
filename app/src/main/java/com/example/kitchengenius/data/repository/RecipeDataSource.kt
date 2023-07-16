package com.example.kitchengenius.data.repository

import com.example.kitchengenius.data.remote.api.RecipeApi
import com.example.kitchengenius.data.remote.dto.asset.toRecipe
import com.example.kitchengenius.domain.model.Recipe
import com.example.kitchengenius.domain.repository.RecipeRepository
import javax.inject.Inject

class RecipeDataSource @Inject constructor(
    private val recipeApi: RecipeApi,
): RecipeRepository {

    override suspend fun getRecipes(): List<Recipe> {
        val recipeList = recipeApi.getRecipes()
        return recipeList?.map { it.toRecipe() } ?: emptyList()
    }

    override suspend fun getFiltredRecipes(filter: String): List<Recipe> {
        val recipeList = recipeApi.getFiltredRecipes(filter)
        return recipeList?.map { it.toRecipe() } ?: emptyList()
    }
}