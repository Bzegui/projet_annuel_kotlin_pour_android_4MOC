package com.example.kitchengenius.domain.interactor

import javax.inject.Inject

data class RecipeInteractor @Inject constructor(
    val getRecipesUC: GetRecipesUC,
    val getFiltredRecipesUC: GetFiltredRecipesUC
)