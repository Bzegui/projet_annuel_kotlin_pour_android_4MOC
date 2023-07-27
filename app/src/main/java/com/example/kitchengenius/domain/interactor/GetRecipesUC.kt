package com.example.kitchengenius.domain.interactor

import com.example.kitchengenius.common.Resource
import com.example.kitchengenius.domain.model.Recipe
import com.example.kitchengenius.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRecipesUC @Inject constructor(private val repository: RecipeRepository){
    operator fun invoke(): Flow<Resource<List<Recipe>>> = flow{
        try {
            emit(Resource.Loading())
            val recipes = repository.getRecipes()
            emit(Resource.Success(recipes))
        }catch (e: Exception){
            emit(Resource.Error(message = "Error with ${e.localizedMessage}"))
        }
    }
}