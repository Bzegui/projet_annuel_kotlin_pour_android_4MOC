package com.example.kitchengenius.domain.interactor

import com.example.kitchengenius.common.Resource
import com.example.kitchengenius.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateLikeUserUC @Inject constructor(private val repository: RecipeRepository){
    operator fun invoke(firebaseId: String, recipeId: String): Flow<Resource<Unit>> = flow{
        try {
            emit(Resource.Loading())
            repository.addLikeUser(firebaseId, recipeId)
            emit(Resource.Success(Unit))
        }catch (e: Exception){
            emit(Resource.Error(message = "Error with ${e.localizedMessage}"))
        }
    }
}