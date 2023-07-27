package com.example.kitchengenius.domain.interactor

import android.util.Log
import com.example.kitchengenius.common.Resource
import com.example.kitchengenius.domain.model.Recipe
import com.example.kitchengenius.domain.model.User
import com.example.kitchengenius.domain.repository.RecipeRepository
import com.example.kitchengenius.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserByIdUC @Inject constructor(private val repository: UserRepository){
    operator fun invoke(idFirebase: String): Flow<Resource<User>> = flow{
        Log.d("ZZZ", "GET USER BY ID UC")
        try {
            emit(Resource.Loading())
            val user = repository.getUserByIdFirebase(idFirebase)
            Log.d("ZZZ", "--------------")
            Log.d("ZZZ",user.toString())
            if(user != null){
                Log.d("ZZZ", "------OKKKKKK--------")
                emit(Resource.Success(user))
            }else{
                emit(Resource.Error(message = "Error user unknown"))
            }
        }catch (e: Exception){
            emit(Resource.Error(message = "Error with ${e.localizedMessage}"))
        }
    }
}
