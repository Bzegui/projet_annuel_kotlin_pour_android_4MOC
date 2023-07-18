package com.example.kitchengenius.data.repository

import com.example.kitchengenius.common.Resource
import com.example.kitchengenius.domain.model.User
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    override suspend fun register(email: String, password: String): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            val result = firebaseAuth.createUserWithEmailAndPassword(email,password).await()
            emit(Resource.Success(result))
            val firebaseId = result?.user?.uid
            val user = User(idFirebase = firebaseId.toString(), likes = emptyList())
            // Send the user object to the API
            sendUser(user)

        }.catch {
            emit(Resource.Error(it.message.toString()))
        }
    }

    override suspend fun login(email: String, password: String): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            val result = firebaseAuth.signInWithEmailAndPassword(email.toString(),
                password.toString()
            ).await()
            emit(Resource.Success(result))
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }
    }
    override suspend fun sendUser(user: User) {
            sendUser(user)
        // apiService.createUser(user).enqueue(...)
    }

}