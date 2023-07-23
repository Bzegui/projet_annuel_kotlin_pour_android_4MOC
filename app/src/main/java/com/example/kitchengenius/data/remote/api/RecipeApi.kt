package com.example.kitchengenius.data.remote.api

import com.example.kitchengenius.data.remote.dto.asset.RecipeDto
import com.example.kitchengenius.domain.model.User
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.POST
import retrofit2.Response
import retrofit2.http.Path

interface RecipeApi {

    @GET("recipes")
    suspend fun getRecipes(): List<RecipeDto>?

    @GET("recipes")
    suspend fun getFiltredRecipes(@Query("filter") filter: String): List<RecipeDto>?

    @GET("recipes/{id}")
    suspend fun getRecipeById(@Path("id") id: String): RecipeDto?


    @POST("users")
    suspend fun addUser(@Body user: User): Response<ResponseBody>
}