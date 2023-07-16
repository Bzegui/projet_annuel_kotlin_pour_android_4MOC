package com.example.kitchengenius.data.repository
import com.example.kitchengenius.common.Resource
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun register(email: String, password: String): Flow<Resource<AuthResult>>
    suspend fun login(email: String, password: String): Flow<Resource<AuthResult>>

}
