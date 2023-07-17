package com.example.kitchengenius.presentation

import MyAppTheme
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.kitchengenius.R
import com.example.kitchengenius.presentation.navigation.NavigationStack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFFF7A50)),
                    contentAlignment = Alignment.Center

                ) {
                    Text(
                        text = stringResource(R.string.app_name),
                        fontSize = 20.sp
                    )

                    NavigationStack()
                }
            }

        }
    }
}

@Composable
fun topBar(){
    val backgroundColor = Color(0xFFFF7A50)
}