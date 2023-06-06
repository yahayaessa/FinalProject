package com.yahayaessa.finalandroidproject.view.screen

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yahayaessa.finalandroidproject.R
import com.yahayaessa.finalandroidproject.model.PreferenceManager
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navMainController: NavController) {

    val context = LocalContext.current

    val preferenceManager = PreferenceManager(context)
    val tokenCheck =preferenceManager.getToken().isNullOrBlank()

    LaunchedEffect(key1 = true) {
        delay(2570)

        if (tokenCheck) {
            preferenceManager.saveUserId(-1)
            navMainController.navigate("OnBoardingScreen")

        } else {
            navigateToHomeScreen(context)
        }
    }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xff346EDF), Color(0xff6FC8FB)),
                    start = Offset(0f, 0f),
                    end = Offset.Infinite
                )
            )
    ) {


        Image(
            painter = painterResource(id = R.drawable.app_icon),
            contentDescription = "app_icon",
            modifier = Modifier
                .width(150.dp)
                .height(150.dp)
                .align(alignment = Alignment.Center)
        )


    }
}

private fun navigateToHomeScreen(context: Context) {
    val intent = Intent(context, HomeScreen::class.java).apply {}
    context.startActivity(intent)
}


