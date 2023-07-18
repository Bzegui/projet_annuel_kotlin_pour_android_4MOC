package com.example.kitchengenius.domain.model

data class User(
    val idFirebase: String,
    val likes: List<String> = emptyList(),
)