package com.vimal.rainyshop.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import com.vimal.rainyshop.pages.CartPage
import com.vimal.rainyshop.pages.HomePage
import com.vimal.rainyshop.pages.ProfilePage
import com.vimal.rainyshop.pages.SettingsPage

@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    val navItemList = listOf(
        NavItem("Home", Icons.Default.Home),
        NavItem("Cart", Icons.Default.ShoppingCart),
        NavItem("User", Icons.Default.Face),
        NavItem("Settings", Icons.Default.Settings),
    )

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = index==selectedIndex,
                        onClick = {
                            selectedIndex = index
                        },
                        icon = {
                            Icon(
                                imageVector = navItem.icon,
                                contentDescription = navItem.label
                            )
                        },
                        label = {
                            Text(text = navItem.label)
                        }

                    )
                }
            }
        }
    ) {
        ContentScreen(modifier = modifier.padding(it), selectedIndex, navController)
    }
}

@Composable
fun ContentScreen(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    navController: NavHostController
) {
    when(selectedIndex) {
        0 -> HomePage(modifier)
        1 -> CartPage(modifier)
        2 -> ProfilePage(modifier, navController)
        3 -> SettingsPage(modifier)
    }
}

data class NavItem(
    val label: String,
    val icon: ImageVector
)