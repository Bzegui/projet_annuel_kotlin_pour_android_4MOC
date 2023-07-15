package com.example.kitchengenius.presentation.viewmodel
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kitchengenius.data.repository.AuthRepository
import com.example.kitchengenius.common.Resource
import com.example.kitchengenius.presentation.screens.login_screen.GoogleSignInState
import com.example.kitchengenius.presentation.screens.login_screen.SignInState
import com.google.firebase.auth.AuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    val _signInState = Channel<SignInState>()
    val signInState = _signInState.receiveAsFlow()

    val _googleState = mutableStateOf(GoogleSignInState())
    val googleState: State<GoogleSignInState> = _googleState

    fun googleSignIn(credential: AuthCredential) = viewModelScope.launch {
        repository.googleSignIn(credential).collect { result ->
            when (result) {
                is Resource.Success -> {
                    _googleState.value = GoogleSignInState(isSuccess = result.data)
                }
                is Resource.Loading -> {
                    _googleState.value = GoogleSignInState(isLoading = true)
                }
                is Resource.Error -> {
                    _googleState.value = GoogleSignInState(isError = result.message!!)
                }
            }

        }
    }

    fun loginUser(email: String, password: String) = viewModelScope.launch {
        repository.login(email,password).collect { result ->
            when(result) {
                is Resource.Success ->  {
                    _signInState.send(SignInState(isSuccess = "Sign In Success"))
                }
                is Resource.Loading ->  {
                    _signInState.send(SignInState(isLoading = true))
                }
                is Resource.Error ->  {
                    _signInState.send(SignInState(isError = result.message))
                }
            }

        }
    }
}