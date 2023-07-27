package com.example.kitchengenius.presentation.screens.edit_recipe_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.kitchengenius.R
import com.example.kitchengenius.presentation.screens.recipe_detail.RecipeDetailViewModel
import androidx.compose.runtime.mutableStateOf as mutableStateOf1
import androidx.compose.ui.Alignment.Companion as ComposeUiAlignment


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditRecipeScreen (
    navController: NavController,
    viewModel: EditRecipeViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    var recipeSelected = uiState.navigateToRecipeDetail

    Logo()

    var recipeTitle by rememberSaveable { mutableStateOf1("") }
    var description by rememberSaveable { mutableStateOf1("") }
    var tags by rememberSaveable { mutableStateOf1("") }
    var process by rememberSaveable { mutableStateOf1("") }
    var nbPeople by rememberSaveable { mutableStateOf1("") }
    var time by rememberSaveable { mutableStateOf1("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current


    //val recipe = navController.
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
                text = "Modifie Recipe",
                color = Color(0xFFADADAD),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )

            if (recipeSelected != null) {
                recipeTitle = recipeSelected.title
                tags = recipeSelected.tags.joinToString(separator = ", ")

                // Recipe title
                TitleTextField(recipeTitle) {
                    recipeTitle = it
                }

                //tags
                TitleTextField(tags) {
                    tags = it
                }
            }

            //for how many person
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, start = 20.dp, end = 20.dp),
            ) {
                if (recipeSelected != null) {
                    Text(text = "For",
                        color = Color(0xFFADADAD),
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,)
                }
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(height = 50.dp, width = 50.dp)
                        .padding(start = 210.dp),
                    value = nbPeople,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    onValueChange = {
                        nbPeople = it
                    },
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true,
                    placeholder = {
                        if (recipeSelected != null) {
                            Text(text = recipeSelected.nbPeople.toString(),
                                color = Color.Black,
                                fontSize = 11.sp,
                            )
                        }
                    }
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

                    value = time,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    onValueChange = {
                        time = it
                    },
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true,
                    placeholder = {
                        if (recipeSelected != null) {
                            Text(text = recipeSelected.time.toString(),
                                color = Color.Black,
                                fontSize = 11.sp,
                            )
                        }
                    }

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
                    //.size(height = 57.dp, width = 100.dp)
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
                singleLine = false,
                placeholder = {
                    if (recipeSelected != null) {
                        Text(text = recipeSelected.description,
                            color = Color.Black,
                            fontSize = 11.sp,
                        )
                    }
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
                singleLine = false,
                placeholder = {
                    if (recipeSelected != null) {
                        Text(text = recipeSelected.process,
                            color = Color.Black,
                            fontSize = 11.sp,)
                    }
                }
            )
            //add picture to the recipe
            AddImage()
            Spacer(modifier = Modifier.height(8.dp))
            RegisterBouton()
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
        Text(text = "Change image" , color = Color(0xFFADADAD),
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,)
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 40.dp),
            onClick = {
            }
        ) {
            Text(text = "Chose your image",
                color = Color.White,)
        }
    }
}
@Composable
fun RegisterBouton() {
    Button(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp, start = 20.dp, end = 20.dp),
        colors = ButtonDefaults.buttonColors(
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(text = "Register", color = Color.White, modifier = Modifier.padding(7.dp))
    }
}

@Composable
fun Logo() {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitleTextField(fieldName : String, onFieldValueChange: (String) -> Unit) {
    var fieldValue by remember { mutableStateOf1(fieldName) }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .size(height = 57.dp, width = 57.dp)
            .padding(top = 5.dp, start = 20.dp, end = 20.dp),
        value = fieldName,
        // visualTransformation = VisualTransformation,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        onValueChange = {
            fieldValue = it
            onFieldValueChange(it)
        },
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        placeholder = {
            Text(text = fieldName,
                color = Color.Black,
                fontSize = 11.sp,)
        }
    )
}

