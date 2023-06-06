package com.yahayaessa.finalandroidproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yahayaessa.finalandroidproject.view.screen.*

@Composable
fun Navigation() {
    val navMainController = rememberNavController()

    NavHost(navController = navMainController, startDestination = "SplashScreen") {
        composable(route = "SplashScreen") {
            SplashScreen(navMainController = navMainController)
        }

        composable(route = "OnBoardingScreen") {
            OnBoardingScreen(navMainController = navMainController)
        }

        composable(route = "OnBoardingScreen2") {
            OnBoardingScreen2(navMainController = navMainController)
        }

        composable(route = "OnBoardingScreen3") {
            OnBoardingScreen3(navMainController = navMainController)
        }



    }
}