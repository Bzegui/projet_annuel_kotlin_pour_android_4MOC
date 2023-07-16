package com.example.kitchengenius.data.remote.api

import com.example.kitchengenius.data.remote.dto.asset.RecipeDto
import retrofit2.http.GET

interface RecipeApi {

    @GET("recipes")
    suspend fun getRecipes(): List<RecipeDto>?
}