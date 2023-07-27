package com.example.kitchengenius.domain.repository

import com.example.kitchengenius.domain.model.User

interface UserRepository{
    suspend fun addUser(user: User)
    suspend fun getUserByIdFirebase(idFirebase: String): User?
}