package com.example.quickstore

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "Home", Icons.Filled.Home)
    object Categories : Screen("categories", "Categories", Icons.AutoMirrored.Filled.List)
    object Profile : Screen("profile", "Profile", Icons.Filled.Person)
}


@Composable
fun MyNavigationBar() {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf(Screen.Home, Screen.Categories, Screen.Profile)

    NavigationBar {
        items.forEachIndexed { index, screen ->
            NavigationBarItem(
                icon = { Icon(screen.icon, contentDescription = screen.title) },
                label = { Text(screen.title) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                }
            )
        }
    }
}