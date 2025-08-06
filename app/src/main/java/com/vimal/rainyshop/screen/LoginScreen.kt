package com.vimal.rainyshop.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.vimal.rainyshop.R
import com.vimal.rainyshop.ui.theme.YellowSZ
import com.vimal.rainyshop.utils.AppUtil
import com.vimal.rainyshop.viewmodel.AuthViewModel

@Composable
fun LoginScreen(modifier: Modifier = Modifier, navController: NavHostController, authViewModel: AuthViewModel = viewModel()) {
    var email by remember {
        mutableStateOf(value = "")
    }

    var password by remember {
        mutableStateOf(value = "")
    }

    var isLoading by remember {
        mutableStateOf(false)
    }

    var context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(all = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {

        Image(
            painter = painterResource(id = R.drawable.loginscreenbanner),
            contentDescription = "Signup Banner",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Hi there!",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                fontSize = 30.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Stay dry and login to your account",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                fontSize = 22.sp,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Center,
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text(text = "Email Address")
            },
            modifier = Modifier.fillMaxWidth()

        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text(text = "Password")
            },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()

        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                isLoading = true
                authViewModel.login(email, password) { success, errorMessage ->
                    if(success) {
                        isLoading = false
                        navController.navigate(route = "home") {
                            popUpTo("auth") {
                                inclusive = true
                            }
                        }
                    } else {
                        isLoading = false
                        AppUtil.showToast(
                            context,
                            errorMessage?:"Something went wrong..."
                        )
                    }
                }
            },
            enabled = !isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            colors = ButtonColors(
                containerColor = YellowSZ,
                contentColor = Color.Black,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = Color.Transparent,
            )
        ) {
            Text(
                text = if(isLoading) "Logging In" else "Log In",
                fontSize = 22.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        FloatingActionButton(
            onClick = {
                navController.navigate(route = "auth")
            },
            shape = CircleShape,
            containerColor = YellowSZ,
            contentColor = Color.Black,
            content = {
                Icon(
                    painter = painterResource(R.drawable.returnhome),
                    contentDescription = "Return Home"
                )
            },
            modifier = Modifier.offset(150.dp, 0.dp)
        )
    }
}