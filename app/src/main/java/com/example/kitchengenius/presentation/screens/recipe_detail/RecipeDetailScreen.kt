package com.example.kitchengenius.presentation.screens.recipe_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.kitchengenius.R
import com.example.kitchengenius.domain.model.Recipe
import com.example.kitchengenius.presentation.screens.recipe_list.RecipeItemList
import com.example.kitchengenius.presentation.screens.recipe_list.UiState
import com.example.kitchengenius.presentation.screens.recipe_list.searchRecipeTextField
import com.example.kitchengenius.presentation.ui.theme.BaseColor


@Composable
fun RecipeDetailScreen(
    navController: NavController,
    recipeId: String,
    viewModel: RecipeDetailViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsState()
    val recipe = viewModel.getRecipeById(id = recipeId)
    RecipeDetailContent(uiState = uiState.value, recipe = recipe)
}

@Composable
fun RecipeDetailContent(uiState: UiState, recipe: Unit){
    Column {
        Row(modifier = Modifier
            .padding(top = 32.dp)
            .fillMaxWidth()) {
            Image(
                painter = painterResource(R.drawable.logo_app),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .padding(start = 32.dp)
                    .size(50.dp)
            )
            Text(modifier = Modifier.padding(top = 10.dp),text = "Kitchen Genius",style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.White))
        }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 32.dp)
            .background(color = Color.White, shape = RoundedCornerShape(16.dp))
    ) {
        when{
            uiState.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                ) {
                    CircularProgressIndicator()
                }
            }
            uiState.navigateToRecipeDetail == null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                ) {
                    Text(
                        text = "Pas de recette",
                        color = Color.White,
                        modifier = Modifier
                            .size(100.dp),
                        style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    )
                }
            }

            uiState.error.isNotEmpty() -> {
                Text(text = "Erreur")
            }
            uiState.navigateToRecipeDetail != null -> {
                var recipeSelected = uiState.navigateToRecipeDetail
                Column(
                    modifier = Modifier
                        .fillMaxSize()

                ) {
                    TopIcons()
                    RecipeDetailTop(recipe = recipeSelected)
                    TagItems(tags = recipeSelected.tags)
                    Text(
                        text = recipeSelected.description,
                        style = TextStyle(fontSize = 16.sp),
                        modifier = Modifier.padding(bottom = 16.dp, start = 16.dp)
                    )
                    Text(
                        text = "Préparation",
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(bottom = 8.dp, start = 16.dp)
                    )
                    Text(
                        text = recipeSelected.process,
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .padding(16.dp)
                    )
                }
            }
        }
        }
    }
}

@Composable
fun TopIcons(){
    var heartFilled = true
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.End
    ) {
        ClickableIcon(
            imageVector = Icons.Default.Create,
            onClick = { /* Réagir au clic sur l'icône crayon */ }
        )
        ClickableIcon(
            imageVector = Icons.Default.Clear,
            onClick = { /* Réagir au clic sur l'icône croix */ }
        )
        ClickableIcon(
            imageVector = if (heartFilled) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            onClick = { heartFilled = !heartFilled }
        )
    }
}

@Composable
fun ClickableIcon(imageVector: ImageVector, onClick: () -> Unit) {
    Icon(
        imageVector = imageVector,
        contentDescription = null,
        tint = Color(0xFFFF7A50),
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onClick)
    )
}
@Composable
fun RecipeDetailTop(recipe: Recipe){
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(painter = rememberAsyncImagePainter(model = recipe.image,placeholder = painterResource(
            id = R.drawable.recipe_placeholder), error = painterResource(
            id = R.drawable.recipe_placeholder)
        ),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .padding(8.dp),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            androidx.compose.material.Text(
                text = recipe.title,
                style = TextStyle(fontSize = 22.sp),
                modifier = Modifier.padding(bottom = 16.dp),
                maxLines = 2
            )

            Row {
                Image(
                    painter = painterResource(R.drawable.fork),
                    contentDescription = "Contact profile picture",
                    modifier = Modifier.size(20.dp)
                )
                androidx.compose.material.Text(
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
                androidx.compose.material.Text(
                    text = recipe.time.toString()+ " min",
                    style = TextStyle(fontSize = 14.sp),
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }
    }
}
@Composable
fun TagItems(tags: List<String>){
    LazyRow(modifier = Modifier.padding(10.dp),contentPadding = PaddingValues(all = 8.dp)){
        items(tags){
            TagItem(text = it )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Composable
fun TagItem(text: String) {
    Box(
        modifier = Modifier
            .background(color = BaseColor, shape = RoundedCornerShape(8.dp))
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "#$text",
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        )
    }
}

@Preview
@Composable
fun SimpleComposablePreview() {
    TagItem(text = "Francef eirgj oireg ")
    //RecipeDetailContent(Recipe("test","Boeuf bourguingnon","incroyable recett ncroyable recett ncroyable recette ",12,60, listOf("Bon","Francais"),"https://img.lemde.fr/2022/02/10/145/184/1183/788/800/0/75/0/711057b_169085-3259764.jpg","Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr , Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr ,Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr ,Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr ,Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr ,Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr ,Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr ,Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr ,Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr ,Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr ,Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr ,Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr ,Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr Se laver les main et z roifnf rfrofueiof e eiorgfh reiutezr "))

}



