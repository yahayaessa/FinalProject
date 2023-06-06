package com.yahayaessa.finalandroidproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.yahayaessa.finalandroidproject.view.items.BottomBarItems
import com.yahayaessa.finalandroidproject.view.screen.MoreScreen
import com.yahayaessa.finalandroidproject.view.screen.OrdersScreen
import com.yahayaessa.finalandroidproject.view.screen.ServiceScreen
import com.yahayaessa.finalandroidproject.view.screen.UserScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarItems.Service.route
    ) {
        composable(route = BottomBarItems.Service.route) {
            ServiceScreen()

        }
        composable(route = BottomBarItems.Orders.route) {
            OrdersScreen()
        }
        composable(route = BottomBarItems.User.route) {
            UserScreen()
        }
        composable(route = BottomBarItems.More.route) {
            MoreScreen()
        }


    }
}