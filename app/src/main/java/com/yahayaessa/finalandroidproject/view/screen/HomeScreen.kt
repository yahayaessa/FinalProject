package com.yahayaessa.finalandroidproject.view.screen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.yahayaessa.finalandroidproject.navigation.BottomNavGraph
import com.yahayaessa.finalandroidproject.view.items.BottomBarItems
import com.yahayaessa.finalandroidproject.viewModel.LoginViewModel


class HomeScreen : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Scaffold(
                bottomBar = { BottomBar(navController = navController) },

                ) {
                BottomNavGraph(navController = navController)

            }
        }
    }

    override fun onBackPressed() {
    }
}


@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarItems.Service,
        BottomBarItems.Orders,
        BottomBarItems.User,
        BottomBarItems.More,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val gradientColors = listOf(
        Color(0xFF2868E4),
        Color(0xFF1491D8)
    )




    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.linearGradient(
                    colors = gradientColors
                )
            )
    ) {

        BottomNavigation(
            modifier = Modifier.background(Color.Transparent),
            backgroundColor = Color.Transparent,
            elevation = 0.dp,

            ) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController,
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarItems,
    currentDestination: NavDestination?,
    navController: NavHostController,

    ) {

    val selectedIconColor = Color.White // Set the selected icon color here
    val unselectedIconColor = Color(0x74FFFEFE) // Set the unselected icon color here

    BottomNavigationItem(


        label = {
            val titleColor =
                if (currentDestination?.hierarchy?.any { it.route == screen.route } == true) {
                    selectedIconColor
                } else {
                    unselectedIconColor
                }
            Text(
                text = screen.title,
                color = titleColor,
                fontSize = 10.sp,
                   
            )
        },

        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,


        icon = {
            val iconColor =
                if (currentDestination?.hierarchy?.any { it.route == screen.route } == true) {
                    selectedIconColor
                } else {
                    unselectedIconColor
                }
            Image(

                painter = painterResource(id = screen.icon),
                contentDescription = "Navigation Icon",
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp),
                colorFilter = ColorFilter.tint(iconColor)


            )
        },
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },


        )
}