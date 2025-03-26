package com.example.quickStock.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.quickStock.Home.ProductCategoryGrid

@Composable
fun MainScreen(onClick: (String) -> Unit,) {
    Column(modifier = Modifier.fillMaxSize()) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            ProductCategoryGrid()
        }
    }
}

@Composable
fun Main3(onClick: (String) -> Unit) {
    Text(text = "puto",
        fontSize = 22.sp,
        textAlign = TextAlign.Center)
}

@Composable
fun Main4(onClick: (String) -> Unit) {
    Text(text = "omggggggggggggggggg",
        fontSize = 22.sp,
        textAlign = TextAlign.Center)
}

@Composable
fun Main2(onClick: (String) -> Unit) {
    Text(text = "o fuck aca prendo camara",
        fontSize = 22.sp,
        textAlign = TextAlign.Center)
}

@Composable
fun NavHostComposable(innerPadding: PaddingValues, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = LearningAndroidScreen.Home.name,
        modifier = Modifier.fillMaxSize().padding(innerPadding).padding(20.dp)
    ) {

        composable(route = LearningAndroidScreen.Recipe.name) {
            Main3(
                onClick = { navController.navigate(it) }
            )
        }

        composable(route = LearningAndroidScreen.Home.name) {
            MainScreen(
                onClick = { navController.navigate(it) }
            )
        }

        composable(route = LearningAndroidScreen.AddProduct.name) {
            Main2(
                onClick = { navController.navigate(it) }
            )
        }

        composable(route = LearningAndroidScreen.User.name) {
            Main4(
                onClick = { navController.navigate(it) }
            )
        }

    }
}