package com.example.kitchengenius.data.remote.dto.asset

import com.example.kitchengenius.domain.model.Recipe
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecipeDto(
    @Json(name = "description")
    val description: String,
    @Json(name = "_id")
    val id: String,
    @Json(name = "image")
    val image: String,
    @Json(name = "nbPeople")
    val nbPeople: Int,
    @Json(name = "process")
    val process: String,
    @Json(name = "tags")
    val tags: List<String>,
    @Json(name = "time")
    val time: Int,
    @Json(name = "title")
    val title: String,
    @Json(name = "__v")
    val v: Int
)

fun RecipeDto.toRecipe(): Recipe = Recipe(
    _id = id,
    title = title ?: "Recette sans titre",
    description = description ?: "Pas de description",
    nbPeople =  nbPeople ?: 2,
    time = time ?: 0,
    tags = tags ?: emptyList(),
    image = image ?: "",
    process =  process ?: ""
)