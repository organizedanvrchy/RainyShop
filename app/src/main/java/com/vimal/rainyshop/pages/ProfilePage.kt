package com.vimal.rainyshop.pages

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun ProfilePage(modifier: Modifier = Modifier, navController: NavController) {
    Text(text = "Profile Page")
    Button(
        onClick = { navController.navigate("auth") }
    ) {
        Text(text = "Logout")
    }
}