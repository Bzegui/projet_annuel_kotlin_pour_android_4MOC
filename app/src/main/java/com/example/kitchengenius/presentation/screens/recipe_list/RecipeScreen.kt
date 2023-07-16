package com.example.kitchengenius.presentation.screens.recipe_list

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.kitchengenius.R
import com.example.kitchengenius.domain.model.Recipe

@Composable
fun RecipeScreen(
    navController: NavController,
    viewModel: RecipeViewModel = hiltViewModel(),
    //onItemClick: (id: String) -> Unit
) {
    val uiState = viewModel.uiState.collectAsState()
    RecipeScreenContent(
        uiState = uiState.value,
        onButtonClick = {
            viewModel.onEventChanged(RecipeEvent.OnButtonClicked)
        }
    ) {
       // onItemClick(it)
    }
}

@Composable
fun RecipeScreenContent(uiState: UiState,
                        onButtonClick: () -> Unit,
                        onItemClick: (id: String) -> Unit){
    Column(modifier = Modifier.padding(16.dp)) {
        Row(modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth()) {
            Image(
                painter = painterResource(R.drawable.logo_app),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .padding(start = 20.dp)
                    .size(50.dp)
            )
            searchRecipeTextField(modifier = Modifier.weight(1f))
        }

        when{
            uiState.isLoading -> {
                Text(text = "Chargement...")
            }

            uiState.recipes.isEmpty() ->{
                Text(text = "pas de recette")
            }

            uiState.recipes.isNotEmpty() -> {
                LazyColumn {
                    items(uiState.recipes) {
                        RecipeItemList(recipe = it)
                    }
                }
            }

            uiState.error.isNotEmpty() -> {
                Text(text = "Erreur")
            }
        }
    }
}

@Composable
fun searchRecipeTextField(modifier: Modifier = Modifier){
    val textState = remember { mutableStateOf("") }

    TextField(
        value = textState.value,
        onValueChange = { textState.value = it },
        textStyle = TextStyle(fontSize = 16.sp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(24.dp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color(0xFFFF7A50) // Couleur orange
            )
        },
        placeholder = { Text("Search...") }
    )
}

@Composable
fun RecipeItemList(recipe: Recipe){
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(8.dp),
            )
            .background(color = Color.White, shape = RoundedCornerShape(16.dp))
    ) {
        Image(painter = rememberAsyncImagePainter(model = recipe.image), contentDescription = null, modifier = Modifier.size(100.dp), contentScale = ContentScale.FillBounds)
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = recipe.title,
                style = TextStyle(fontSize = 22.sp),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row {
                Image(
                    painter = painterResource(R.drawable.fork),
                    contentDescription = "Contact profile picture",
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = recipe.nbPeople.toString() + " pers",
                    style = TextStyle(fontSize = 14.sp),
                    modifier = Modifier.padding(start = 4.dp)
                )
                Box(modifier = Modifier.size(16.dp))
                Image(
                    painter = painterResource(R.drawable.clock) ,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = recipe.time.toString()+ " min",
                    style = TextStyle(fontSize = 14.sp),
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }
    }
}