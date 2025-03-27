package com.example.quickStock.navigation

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.quickStock.addProducts.ProductSurvey
import com.example.quickStock.home.HomeScreen
import com.example.quickStock.userConfig.UserSettingsPagePreview


@Composable
fun Main3(onClick: (String) -> Unit) {
    Text(text = "puto",
        fontSize = 22.sp,
        textAlign = TextAlign.Center)
}


@Composable
fun Main2(onClick: (String) -> Unit) {
//    BarcodeScannerScreen { capturedImageUri ->
//        // Handle the captured image URI here
//        if (capturedImageUri != null) {
//            Log.d("BarcodeScanner", "Image captured: $capturedImageUri")
//            // You can now pass this URI to a barcode scanning library
//            //or display the image to the user.
//        } else {
//            Log.e("BarcodeScanner", "Failed to capture image.")
//        }
//    }

    ProductSurvey(onProductAdded = {
        Log.d("ProductSurvey", "Product added: $it")
    })
}

@Composable
fun NavHostComposable(innerPadding: PaddingValues, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavBarNames.Home.name,
        modifier = Modifier.fillMaxSize().padding(innerPadding).padding(20.dp)
    ) {

        composable(route = NavBarNames.Home.name) {
            HomeScreen(
                onClick = { navController.navigate(it) }
            )
        }

        composable(route = NavBarNames.Recipe.name) {
            Main3(
                onClick = { navController.navigate(it) }
            )
        }

        composable(route = NavBarNames.AddProduct.name) {
            Main2(
                onClick = { navController.navigate(it) }
            )
        }

        composable(route = NavBarNames.User.name) {
            UserSettingsPagePreview(
                onClick = { navController.navigate(it) }
            )
        }

    }
}