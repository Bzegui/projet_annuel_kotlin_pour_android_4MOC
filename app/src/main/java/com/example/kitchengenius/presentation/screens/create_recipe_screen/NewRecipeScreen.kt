package com.example.kitchengenius.presentation.screens.create_recipe_screen

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kitchengenius.R
import androidx.compose.runtime.mutableStateOf as mutableStateOf1
import androidx.compose.ui.Alignment.Companion as ComposeUiAlignment


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewRecipeScreen (
    navController: NavController,
) {
    Logo_signin()

    var recipeName by rememberSaveable { mutableStateOf1("") }
    var description by rememberSaveable { mutableStateOf1("") }
    var tags by rememberSaveable { mutableStateOf1("") }
    var process by rememberSaveable { mutableStateOf1("") }
    var nbPeople by rememberSaveable { mutableStateOf1(0) }
    var selectedImageUri by remember { mutableStateOf1<Uri?>(null)}
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    //val state = viewModel.NewRecipeViewModel.collectAsState(initial = null)

    Box(
        modifier = Modifier
            .offset(0.dp, 85.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding()
                .background(Color.White, shape = RoundedCornerShape(25.dp)),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = ComposeUiAlignment.CenterHorizontally,

            ) {
            // Start
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 20.dp, end = 20.dp),
                text = "New Recipe",
                color = Color(0xFFADADAD),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                )
            // Recipe name
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(height = 57.dp, width = 57.dp)
                    .padding(top = 5.dp, start = 20.dp, end = 20.dp),
                value = recipeName,
                visualTransformation = VisualTransformation.None,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                onValueChange = {
                    recipeName = it
                },
                shape = RoundedCornerShape(8.dp),
                singleLine = true,
                placeholder = {
                    Text(text = "Name",
                        color = Color(0xFFADADAD),
                        fontSize = 10.sp,)
                }
            )
            //Recipe categorie
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(height = 57.dp, width = 57.dp)
                    .padding(top = 5.dp, start = 20.dp, end = 20.dp),
                value = tags,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                ),
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                onValueChange = {
                    tags = it
                },
                shape = RoundedCornerShape(8.dp),
                singleLine = true,
                placeholder = {
                    Text(text = "tags",
                        color = Color(0xFFADADAD),
                        fontSize = 10.sp,)
                }
            )
            //for how many person
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 20.dp, end = 20.dp),

            ) {
                Text(text = "For",
                    color = Color(0xFFADADAD),
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,)
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(height = 50.dp, width = 50.dp)
                        .padding(start = 210.dp),
                    value = nbPeople.toString(),
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    onValueChange = {
                        nbPeople = it.toInt()
                    },
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true,
                )
            }
            // Recipe time
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 20.dp, end = 20.dp),

                ) {
                Text(text = "Time"
                    , color = Color(0xFFADADAD),
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,)
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(height = 50.dp, width = 50.dp)
                        .padding(start = 200.dp),

                    value = nbPeople.toString(),
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    onValueChange = {
                        nbPeople = it.toInt()
                    },
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true,

                    )
            }
            // Recipe description
            Text(modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 5.dp, start = 20.dp, end = 20.dp),
                text = "Description",
                color = Color(0xFFADADAD),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,)
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(height = 57.dp, width = 100.dp)
                    .padding(top = 5.dp, start = 20.dp, end = 20.dp),
                value = description,
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                onValueChange = {
                    description = it
                },
                shape = RoundedCornerShape(8.dp),
                singleLine = true,
                placeholder = {
                    Text(text = "description"
                        , color = Color(0xFFADADAD),
                        fontSize = 10.sp,)
                }
            )

            // Recipe process
            Text(modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 5.dp, start = 20.dp, end = 20.dp),
                text = "process",
                color = Color(0xFFADADAD),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,)
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(height = 150.dp, width = 100.dp)
                    .padding(top = 5.dp, start = 20.dp, end = 20.dp),
                value = process,
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                onValueChange = {
                    process = it
                },
                shape = RoundedCornerShape(8.dp),
                singleLine = true,
                placeholder = {
                    Text(text = "process"
                        , color = Color(0xFFADADAD),
                        fontSize = 10.sp,)
                }
            )
            //add picture to the recipe
            AddImage()
            Spacer(modifier = Modifier.height(8.dp))
            SubmitBouton()
        }
    }
}

@Composable
fun AddImage() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp, start = 20.dp, end = 20.dp),
        ) {
        Text(text = "Add image" , color = Color(0xFFADADAD),
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,)
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 70.dp),
            onClick = {
            }
        ) {
            Text(text = "Chose your image",
                color = Color.White,)
        }
    }
}
@Composable
fun SubmitBouton() {
    Button(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp, start = 20.dp, end = 20.dp),
        colors = ButtonDefaults.buttonColors(
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(text = "Submit", color = Color.White, modifier = Modifier.padding(7.dp))
    }
}

@Composable
fun Logo_signin() {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp)
    ) {
        Box {
            Row {
                Image(
                    painter = painterResource(R.drawable.logo_app),
                    contentDescription = "Contact profile picture",
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .size(50.dp)
                )
                Text(
                    text = "kitchen genius",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                )
            }
        }
    }
}

