package com.yahayaessa.finalandroidproject.view.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.yahayaessa.finalandroidproject.CustomButton
import com.yahayaessa.finalandroidproject.R

@Composable
fun OnBoardingScreen(navMainController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.mobile_application_call_taxi),
                contentDescription = "onBoardingImage",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),

                )

            Text(
                text = "Fast reservation with technicians and craftsmen",
                color = Color(0xff0E9CF9),
                modifier = Modifier.padding(top = 80.dp, bottom = 80.dp),
                fontSize = 23.sp,
                   
                textAlign = TextAlign.Center,
            )



            CustomButton(
                onClick = { navMainController.navigate("OnBoardingScreen2") },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(8.dp),
                gradientColors = listOf(Color(0xff346EDF), Color(0xff6FC8FB))
            ) {
                Text(
                    text = "Next",
                       
                    color = Color.White,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp)
                )
            }


        }

    }

}



