package com.example.kitchengenius.domain.interactor

import com.example.kitchengenius.common.Resource
import com.example.kitchengenius.domain.model.Recipe
import com.example.kitchengenius.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetLikedRecipesUC @Inject constructor(private val repository: RecipeRepository){
    operator fun invoke(idFirebase: String): Flow<Resource<List<Recipe>>> = flow{
        try {
            emit(Resource.Loading())
            val recipes = repository.getLikedRecipes(idFirebase = idFirebase)
            emit(Resource.Success(recipes))
        }catch (e: Exception){
            emit(Resource.Error(message = "Error with ${e.localizedMessage}"))
        }
    }


}