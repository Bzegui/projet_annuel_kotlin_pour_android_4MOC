package com.example.kitchengenius.data.remote.api

import com.example.kitchengenius.data.remote.dto.asset.UserDto
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {
   // @POST("users")
    //suspend fun addUser(user: User)
   @GET("users/{idFirebase}")
   suspend fun getUserByIdFirebase(@Path("idFirebase") idFirebase: String): UserDto?
}