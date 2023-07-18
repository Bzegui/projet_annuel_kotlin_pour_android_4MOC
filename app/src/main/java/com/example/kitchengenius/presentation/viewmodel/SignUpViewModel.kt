package com.example.kitchengenius.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kitchengenius.data.repository.AuthRepository
import com.example.kitchengenius.presentation.screens.login_screen.SignInState
import com.example.kitchengenius.common.Resource
import com.example.kitchengenius.domain.model.User
import com.example.kitchengenius.presentation.screens.signup_screen.SignUpState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    val _signUpState = Channel<SignUpState>()
    val signUpState = _signUpState.receiveAsFlow()
    var firebaseId : String = ""

    fun registerUser(email:String, password:String) = viewModelScope.launch {
        repository.register(email,password).collect { result ->
            when(result) {
                is Resource.Success ->  {
                    _signUpState.send(SignUpState(isSuccess = "Sign Up Success"))
                }
                is Resource.Loading ->  {
                    _signUpState.send(SignUpState(isLoading = true))
                }
                is Resource.Error ->  {
                    _signUpState.send(SignUpState(isError = result.message))
                }
            }
        }
    }
}