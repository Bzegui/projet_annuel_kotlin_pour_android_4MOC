package com.example.kitchengenius.presentation.screens.login_screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.kitchengenius.R
import com.example.kitchengenius.navigation.Screens
import com.example.kitchengenius.presentation.viewmodel.SignInViewModel
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment.Companion as ComposeUiAlignment


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen (
    navController: NavController,
    viewModel: SignInViewModel = hiltViewModel()
) {

    Logo_signin()

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.signInState.collectAsState(initial = null)

    Box(
        modifier = Modifier
            .offset(0.dp, 200.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding()
                .background(Color.White, shape = RoundedCornerShape(25.dp)),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = ComposeUiAlignment.CenterHorizontally,


            ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 30.dp, end = 30.dp),
                text = "Sign In",
                color = Color(0xFFADADAD),
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,

                )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 30.dp, end = 30.dp),
                value = email,
                visualTransformation = VisualTransformation.None,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                onValueChange = {
                    email = it
                },
                shape = RoundedCornerShape(8.dp),
                singleLine = true,
                placeholder = {
                    Text(text = "Email")
                }
            )

            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 30.dp, end = 30.dp),
                value = password,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                onValueChange = {
                    password = it
                },
                shape = RoundedCornerShape(8.dp),
                singleLine = true,
                placeholder = {
                    Text(text = "Password")
                }
            )


            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {
                    print("##########################")
                    scope.launch {
                        viewModel.loginUser(email = email, password = password)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 30.dp, end = 30.dp),
                colors = ButtonDefaults.buttonColors(
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(text = "Sign In", color = Color.White, modifier = Modifier.padding(7.dp))
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                if (state.value?.isLoading == true) {
                    CircularProgressIndicator()
                }
            }

            Text(
                modifier = Modifier
                    .padding(15.dp)
                    .clickable {
                        navController.navigate(Screens.SignUpScreen.route)
                    },

                text = "New User? Sign Up",
                fontWeight = FontWeight.Bold, color = Color(0xFFADADAD),
            )
        }
        Spacer(modifier = Modifier.width(20.dp))

        LaunchedEffect(key1 = state.value?.isSuccess) {
            scope.launch {
                if (state.value?.isSuccess?.isNotEmpty() == true) {
                    val success = state.value?.isSuccess
                    Toast.makeText(context, "${success}", Toast.LENGTH_LONG).show()
                    navController.navigate(Screens.RecipeListScreen.route)
                }
            }
        }

        LaunchedEffect(key1 = state.value?.isError) {
            scope.launch {
                if (state.value?.isError?.isNotEmpty() == true) {
                    val error = state.value?.isError
                    Toast.makeText(context, "${error}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}

@Composable
fun Logo_signin() {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box {
            Image(
                painter = painterResource(R.drawable.logo_app),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(150.dp)
                    .padding(top = 50.dp)
            )
        }
        Text(
            text = "kitchen genius",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
        )
    }
}
