package com.example.kitchengenius.data.remote.api

import com.example.kitchengenius.data.remote.dto.asset.RecipeDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApi {

    @GET("recipes")
    suspend fun getRecipes(): List<RecipeDto>?

    @GET("recipes")
    suspend fun getFiltredRecipes(@Query("filter") filter: String): List<RecipeDto>?
}