package com.example.kitchengenius.domain.interactor

import javax.inject.Inject

data class RecipeDetailInteractor @Inject constructor(
    val getRecipesByIdUC: GetRecipeByIdUC,
    val updateLikeUserUC: UpdateLikeUserUC,
    val getUserByIdUC: GetUserByIdUC
)
