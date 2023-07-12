package com.example.kitchengenius.presentation.screens.login_screen

import com.google.firebase.auth.AuthResult

class GoogleSignInState (
    val isSuccess: AuthResult? = null,
    val isLoading: Boolean = false,
    val isError: String = ""
)