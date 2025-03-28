package com.example.quickStock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.quickStock.navigation.NavBar
import com.example.quickStock.navigation.NavHostComposable
import com.example.quickStock.ui.theme.QuickStockTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            QuickStockTheme {
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
