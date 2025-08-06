package com.vimal.rainyshop

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.vimal.rainyshop.screen.AuthScreen
import com.vimal.rainyshop.screen.HomeScreen
import com.vimal.rainyshop.screen.LoginScreen
import com.vimal.rainyshop.screen.SignupScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val isLoggedIn = Firebase.auth.currentUser!=null
    val firstPage = if(isLoggedIn) "home" else "auth"

    NavHost(navController = navController, startDestination = firstPage) {
        composable(route = "auth") {
            AuthScreen(modifier, navController)
        }

        composable(route = "login") {
            LoginScreen(modifier, navController)
        }

        composable(route = "signup") {
            SignupScreen(modifier, navController)
        }

        composable(route = "home") {
            HomeScreen(modifier, navController)
        }
    }
}