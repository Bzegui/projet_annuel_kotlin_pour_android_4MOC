package com.example.kitchengenius.domain.interactor

import com.example.kitchengenius.common.Resource
import com.example.kitchengenius.domain.model.Recipe
import com.example.kitchengenius.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRecipeByIdUC @Inject constructor(private val repository: RecipeRepository){
    operator fun invoke(id: String): Flow<Resource<Recipe>> = flow{
        try {
            emit(Resource.Loading())
            val recipe = repository.getRecipeById(id)
            if(recipe != null) {
                emit(Resource.Success(recipe))
            }else{
                emit(Resource.Error(message = "Error : recette non trouvé"))
            }
        }catch (e: Exception){
            emit(Resource.Error(message = "Error with ${e.localizedMessage}"))
        }
    }
}