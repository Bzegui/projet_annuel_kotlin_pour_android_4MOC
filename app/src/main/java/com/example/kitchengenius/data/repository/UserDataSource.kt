package com.example.kitchengenius.data.repository

import com.example.kitchengenius.data.remote.api.RecipeApi
import com.example.kitchengenius.data.remote.api.UserApi
import com.example.kitchengenius.domain.model.User
import com.example.kitchengenius.domain.repository.RecipeRepository
import com.example.kitchengenius.domain.repository.UserRepository
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val recipeApi: RecipeApi,
): UserRepository {
    override suspend fun addUser(user: User) {
        recipeApi.addUser(user)
    }
}