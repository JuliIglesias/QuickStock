package com.example.quickStock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.quickStock.Home.ProductCategoryGrid
import com.example.quickStock.navigation.NavBar
import com.example.quickStock.navigation.NavHostComposable
import com.example.quickStock.ui.theme.QuickstoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            QuickstoreTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    //color = MaterialTheme.colorScheme.background,
                    bottomBar = {NavBar(navController::navigate)}
                    ) {innerPadding ->
                    NavHostComposable(innerPadding, navController)
                }
            }
        }
    }
}




