package com.vimal.rainyshop

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vimal.rainyshop.screen.AuthScreen
import com.vimal.rainyshop.screen.LoginScreen
import com.vimal.rainyshop.screen.SignupScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "auth") {
        composable(route = "auth") {
            AuthScreen(modifier, navController)
        }

        composable(route = "login") {
            LoginScreen(modifier, navController)
        }

        composable(route = "signup") {
            SignupScreen(modifier, navController)
        }

    }
}