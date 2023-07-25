package com.example.kitchengenius.presentation.screens.edit_recipe_screen

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kitchengenius.common.Resource
import com.example.kitchengenius.domain.interactor.RecipeDetailInteractor
import com.example.kitchengenius.presentation.screens.recipe_list.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class EditRecipeViewModel @Inject constructor(
    //var recipeId : String
    private val interactor: RecipeDetailInteractor,
    savedStateHandle: SavedStateHandle
): ViewModel(){
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()
    private val idRecipe: String = checkNotNull(savedStateHandle["id"])
    init {
        getRecipeById(idRecipe)
    }

    //modifier l'accesseure
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
                    print(resource.data)
                    it.copy(
                        isLoading = false,
                        navigateToRecipeDetail = resource.data,
                        error = ""
                    )
                }
            }
        }.launchIn(viewModelScope.plus(Dispatchers.IO))
    }
}