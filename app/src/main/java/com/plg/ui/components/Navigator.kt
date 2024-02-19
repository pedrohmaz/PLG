package com.plg.ui.components

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.plg.ui.screens.CustomizarInstrumentoScreen
import com.plg.ui.screens.LoginScreen

@Composable
fun Navigator(activity: ComponentActivity) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "login"
    )
    {
        composable("login") {
            LoginScreen(activity = activity,
                aoNavegarParaCustomizarInstrumento =
                { navController.navigate("customizarInstrumento") })
        }
        composable("customizarInstrumento") { CustomizarInstrumentoScreen(activity) }
    }
}