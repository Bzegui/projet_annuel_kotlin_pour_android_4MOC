package com.example.kitchengenius.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kitchengenius.presentation.screens.StartScreen
import com.example.kitchengenius.presentation.screens.login_screen.SignInScreen
import com.example.kitchengenius.presentation.screens.signup_screen.SignUpScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationStack() {
    val navController: NavHostController = rememberNavController()
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

    Scaffold(
        Modifier.padding(bottom = 47.dp),
        bottomBar = { BottomNavigationBar(
            navController = navController,
            bottomBarState = bottomBarState
        ) },
        content = {
            NavHost(
                navController = navController,
                //Set the padding provided by scaffold
                startDestination = Screen.SignInScreen.route
            ) {
                composable(route = Screen.SignInScreen.route) {
                    bottomBarState.value = false
                    SignInScreen(navController)
                }
                composable(route = Screen.StartScreen.route) {
                    StartScreen(navController)
                    bottomBarState.value = true
                }
                composable(route = Screen.SignUpScreen.route) {
                    SignUpScreen(navController)
                    bottomBarState.value = false
                }
            }
        },
    )
}