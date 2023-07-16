package com.example.kitchengenius.presentation.screens.recipe_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kitchengenius.common.Resource
import com.example.kitchengenius.domain.interactor.RecipeInteractor
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
class RecipeViewModel @Inject constructor(
    private val interactor: RecipeInteractor
): ViewModel(){
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        getRecipes()
    }

    internal fun getRecipes(){
        interactor.getRecipesUC().onEach { resource ->
            when(resource){
                is Resource.Error -> _uiState.update {
                    resource.message?.let { it1 -> Log.d("Error", it1) }
                    it.copy(
                        isLoading = false,
                        recipes = emptyList(),
                        error = resource.message ?: "Une erreur est survenue"
                    )
                }
                is Resource.Loading -> _uiState.update {
                    it.copy(
                        isLoading = true,
                        recipes = emptyList(),
                        error = ""
                    )
                }

                is Resource.Success -> _uiState.update {
                    print(resource.data)
                    it.copy(
                        isLoading = false,
                        recipes = resource.data ?: emptyList(),
                        error = ""
                    )
                }
            }
        }.launchIn(viewModelScope.plus(Dispatchers.IO))
    }

    internal fun getFiltredRecipes(filter : String){
        interactor.getFiltredRecipesUC(filter).onEach { resource ->
            when(resource){
                is Resource.Error -> _uiState.update {
                    resource.message?.let { it1 -> Log.d("Error", it1) }
                    it.copy(
                        isLoading = false,
                        recipes = emptyList(),
                        error = resource.message ?: "Une erreur est survenue"
                    )
                }
                is Resource.Loading -> _uiState.update {
                    it.copy(
                        isLoading = true,
                        recipes = emptyList(),
                        error = ""
                    )
                }

                is Resource.Success -> _uiState.update {
                    Log.d("ZZZZ",filter)
                    Log.d("ZZZZ", resource.data.toString())
                    it.copy(
                        isLoading = false,
                        recipes = resource.data ?: emptyList(),
                        error = ""
                    )
                }
            }
        }.launchIn(viewModelScope.plus(Dispatchers.IO))
    }
    private fun testing(id: String){

    }
    fun onEventChanged(event: RecipeEvent){
        when(event){
            RecipeEvent.OnButtonClicked -> getRecipes()
            is RecipeEvent.OnTest -> testing(event.id)
        }
    }

}