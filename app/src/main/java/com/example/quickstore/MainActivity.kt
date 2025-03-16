package com.example.quickstore

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
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.quickstore.Home.ProductCategoryGrid
import com.example.quickstore.ui.theme.QuickstoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuickstoreTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    color = MaterialTheme.colorScheme.background,
                    ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        // Parte superior (cambiante)
        Surface(
            modifier = Modifier
                .weight(1f) // Ocupa todo el espacio restante
                .fillMaxWidth()
                .padding(8.dp),
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            ProductCategoryGrid()
        }

        // Parte inferior (fija)
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondaryContainer)
        ) {
            // Aquí va el contenido fijo (navbar)
            MyNavigationBar()
        }
    }
}


