package com.example.quickStock.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.quickStock.R
import com.example.quickStock.icon.IconType
import com.example.quickStock.icon.MyIcon


data class TabBarItem(
    val title: String,
    val selectedIcon: IconType,
    val unselectedIcon: IconType,
    val badgeAmount: Int? = null
)

@Composable
fun NavBar(
    onNavigate: (String) -> Unit,
) {
    val homeTab = TabBarItem(
        title = LearningAndroidScreen.Home.name,
        selectedIcon = IconType.Drawable(painterResource(R.drawable.ic_home_fill)),
        unselectedIcon = IconType.Drawable(painterResource(R.drawable.ic_home_outline))
    )
    val recipeTab = TabBarItem(
        title = LearningAndroidScreen.Recipe.name,
        selectedIcon = IconType.Drawable(painterResource(R.drawable.ic_recipe_fill)),
        unselectedIcon = IconType.Drawable(painterResource(R.drawable.ic_recipe_outline))
    )
    val profileTab = TabBarItem(
        title = LearningAndroidScreen.User.name,
        selectedIcon = IconType.Vector(Icons.Filled.Person),
        unselectedIcon = IconType.Vector(Icons.Outlined.Person)
    )

    val cameraAddTab = TabBarItem(
        title = LearningAndroidScreen.CameraAdd.name,
        selectedIcon = IconType.Drawable(painterResource(R.drawable.ic_camera_plus_fill)),
        unselectedIcon = IconType.Drawable(painterResource(R.drawable.ic_camera_plus_outline))
    )

    val tabBarItems = listOf(recipeTab, homeTab, cameraAddTab, profileTab)

    TabView(tabBarItems, onNavigate)

}

@Composable
fun TabView(tabBarItems: List<TabBarItem>, onNavigate: (String) -> Unit) {
    var selectedTabIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    NavigationBar {
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
                label = { Text(tabBarItem.title) })
        }
    }
}

@Composable
fun TabBarIconView(
    isSelected: Boolean,
    selectedIcon: IconType,
    unselectedIcon: IconType,
    title: String,
    badgeAmount: Int? = null
) {
    BadgedBox(badge = { TabBarBadgeView(badgeAmount) }) {
        MyIcon(
            icon = if (isSelected) {selectedIcon} else {unselectedIcon},
            contentDescription = title,
            modifier = Modifier.size(32.dp)
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