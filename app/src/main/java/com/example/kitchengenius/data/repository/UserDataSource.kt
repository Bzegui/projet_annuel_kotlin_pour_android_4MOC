package com.example.kitchengenius.data.repository

import com.example.kitchengenius.data.remote.api.UserApi
import com.example.kitchengenius.domain.model.User
import com.example.kitchengenius.domain.repository.UserRepository
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val userApi: UserApi,
): UserRepository {
    override suspend fun sendUserToApi(user: User) {
        userApi.addUser(user)
    }
}