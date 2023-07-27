package com.example.kitchengenius.data.remote.dto.asset

import com.example.kitchengenius.domain.model.User
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDto(
    @Json(name = "_id")
    val id: String,
    @Json(name = "idFirebase")
    val idFirebase: String,
    @Json(name = "likes")
    val likes: List<String>,
)

fun UserDto.toUser(): User = User(
    idFirebase = idFirebase,
    likes = likes
)