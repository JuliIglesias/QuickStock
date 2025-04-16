package com.example.quickStock.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.quickStock.R
import com.example.quickStock.icon.IconType
import com.example.quickStock.icon.MyIcon
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp

data class TabBarItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeAmount: Int? = null
)

@Composable
fun NavBar(
    onNavigate: (String) -> Unit,
) {
    val homeTab = TabBarItem(
        title = NavBarNames.Home.name,
        selectedIcon = ImageVector.vectorResource(R.drawable.ic_home_fill),
        unselectedIcon = ImageVector.vectorResource(R.drawable.ic_home_outline)
    )
    val recipeTab = TabBarItem(
        title = NavBarNames.Recipe.name,
        selectedIcon = ImageVector.vectorResource(R.drawable.ic_recipe_fill),
        unselectedIcon = ImageVector.vectorResource(R.drawable.ic_recipe_outline)
    )
    val profileTab = TabBarItem(
        title = NavBarNames.User.name,
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person
    )

    val cameraAddTab = TabBarItem(
        title = NavBarNames.AddProduct.name,
        selectedIcon = ImageVector.vectorResource(R.drawable.ic_camera_plus_fill),
        unselectedIcon = ImageVector.vectorResource(R.drawable.ic_camera_plus_outline)
    )

    val tabBarItems = listOf(recipeTab, homeTab, cameraAddTab, profileTab)

    TabView(tabBarItems, onNavigate)

}

@Composable
fun TabView(tabBarItems: List<TabBarItem>, onNavigate: (String) -> Unit) {
    var selectedTabIndex by rememberSaveable {
        mutableIntStateOf(1)
    }

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
    ) {
        tabBarItems.forEachIndexed { index, tabBarItem ->
            NavigationBarItem(
                selected = selectedTabIndex == index,
                onClick = {
                    selectedTabIndex = index
                    onNavigate(tabBarItem.title)
                },
                icon = {
                    TabBarIconView(
                        isSelected = selectedTabIndex == index,
                        selectedIcon = tabBarItem.selectedIcon,
                        unselectedIcon = tabBarItem.unselectedIcon,
                        title = tabBarItem.title,
                        badgeAmount = tabBarItem.badgeAmount
                    )
                },
                label = {
                    Text(
                        text = tabBarItem.title,
                    )
                },
                colors = androidx.compose.material3.NavigationBarItemDefaults.colors(
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedTextColor = MaterialTheme.colorScheme.secondary,
                    indicatorColor = Color.Transparent // Color del indicador redondeado
                )
            )
        }
    }
}

@Composable
fun TabBarIconView(
    isSelected: Boolean,
    selectedIcon: ImageVector,
    unselectedIcon: ImageVector,
    title: String,
    badgeAmount: Int? = null
) {
    BadgedBox(badge = { TabBarBadgeView(badgeAmount) }) {
        MyIcon(
            icon = if (isSelected) {selectedIcon} else {unselectedIcon},
            contentDescription = title,
            modifier = Modifier.size(32.dp),
            tint = if (isSelected) {MaterialTheme.colorScheme.onPrimary}
            else {MaterialTheme.colorScheme.secondary}
        )
    }
}

@Composable
fun TabBarBadgeView(count: Int? = null) {
    if (count != null) {
        Badge {
            Text(count.toString())
        }
    }
}