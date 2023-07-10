package com.example.kitchengenius.presentation
import com.google.firebase.auth.FirebaseAuth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import com.example.kitchengenius.navigation.KitchenGeniusNavHost
import com.example.kitchengenius.presentation.ui.theme.KitchenGeniusTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Initialisation de Firebase Auth
        FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        setContent {
            KitchenGeniusTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    KitchenGeniusNavHost()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KitchenGeniusTheme {

    }
}