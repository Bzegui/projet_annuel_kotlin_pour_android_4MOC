package com.example.kitchengenius.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kitchengenius.view_models.CookingRecipesViewModel

@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    onNavigateToCookingRecipesList: () -> Unit,
    cookingRecipesViewModel: CookingRecipesViewModel
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(20.dp)
    ) {

        Button(onClick = onNavigateToCookingRecipesList) {
            Text(
                text = "to cooking recipes list",
            )
        }
    }
}