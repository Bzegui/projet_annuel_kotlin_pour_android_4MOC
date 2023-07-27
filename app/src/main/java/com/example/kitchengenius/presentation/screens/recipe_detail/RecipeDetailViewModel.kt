package com.example.kitchengenius.presentation.screens.recipe_detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.example.kitchengenius.common.Resource
import com.example.kitchengenius.domain.interactor.RecipeDetailInteractor
import com.example.kitchengenius.domain.interactor.RecipeInteractor
import com.example.kitchengenius.domain.model.Recipe
import com.example.kitchengenius.presentation.screens.recipe_list.RecipeEvent
import com.example.kitchengenius.presentation.screens.recipe_list.UiState
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    //var recipeId : String
    private val interactor: RecipeDetailInteractor,
    savedStateHandle: SavedStateHandle
): ViewModel(){
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private val idRecipe: String = checkNotNull(savedStateHandle["id"])

    init {
        getRecipeById(idRecipe)
        getLikeUser()
    }
    private fun getRecipeById(id: String ) {
        interactor.getRecipesByIdUC(id).onEach { resource ->
            when(resource){
                is Resource.Error -> _uiState.update {
                    resource.message?.let { it1 -> Log.d("Error", it1) }
                    it.copy(
                        isLoading = false,
                        navigateToRecipeDetail = null,
                        error = resource.message ?: "Une erreur est survenue"
                    )
                }
                is Resource.Loading -> _uiState.update {
                    it.copy(
                        isLoading = true,
                        navigateToRecipeDetail = null,
                        error = ""
                    )
                }

                is Resource.Success -> _uiState.update {
                    it.copy(
                        isLoading = false,
                        navigateToRecipeDetail = resource.data,
                        error = ""
                    )
                }
            }
        }.launchIn(viewModelScope.plus(Dispatchers.IO))
    }
    private fun getLikeUser(){
        val userId = Firebase.auth.currentUser?.uid
        if (userId == null){
        }
        if(userId != null) {
            interactor.getUserByIdUC(userId).onEach { resource ->
                when(resource){
                    is Resource.Error -> _uiState.update {
                        resource.message?.let { it1 -> Log.d("Error", it1) }
                        it.copy(
                            isLoading = false,
                            currentUser = null,
                            error = resource.message ?: "Une erreur est survenue"
                        )
                    }
                    is Resource.Loading -> _uiState.update {
                        it.copy(
                            isLoading = true,
                            currentUser = null,
                            error = ""
                        )
                    }

                    is Resource.Success -> _uiState.update{
                        it.copy(
                            isLoading = false,
                            currentUser = resource.data,
                            error = ""
                        )
                    }
                }
            }.launchIn(viewModelScope.plus(Dispatchers.IO))
        }
    }

    private fun updateLikeUser(){
        val userId = Firebase.auth.currentUser?.uid

        if (userId != null) {
            interactor.updateLikeUserUC(userId,idRecipe).onEach { resource ->
                when(resource){
                    is Resource.Error -> _uiState.update{
                        it.copy(
                            isLoading = false,
                            error = "Impossible d'ajouter la recette en favoriz"
                        )
                    }
                    is Resource.Loading -> _uiState.update {
                        it.copy(isLoading = true)
                    }
                    is Resource.Success -> _uiState.update {
                        it.copy(isLoading = false)
                    }
                }
            }.launchIn(viewModelScope.plus(Dispatchers.IO))
        }else{
        }
    }

    fun onEventChanged(event: RecipeDetailEvent){
        when(event){
            is RecipeDetailEvent.OnLikedRecipe -> {
                updateLikeUser()

            }
        }
    }
}