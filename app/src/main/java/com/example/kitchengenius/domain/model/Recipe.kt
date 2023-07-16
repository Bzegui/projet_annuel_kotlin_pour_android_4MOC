package com.example.kitchengenius.domain.model

data class Recipe(
    val _id: String,
    val title: String,
    val description: String,
    val nbPeople: Int,
    val time: Int,
    val tags: List<String> = emptyList(),
    val image: String,
    val process: String,
)