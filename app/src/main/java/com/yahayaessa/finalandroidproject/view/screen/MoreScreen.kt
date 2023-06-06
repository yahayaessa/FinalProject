package com.yahayaessa.finalandroidproject.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MoreScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {


        Box(
            Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xff346EDF), Color(0xff6FC8FB)),
                        startX = 0f,
                        endX = Float.POSITIVE_INFINITY
                    ),
                    shape = RoundedCornerShape(bottomStartPercent = 50, bottomEndPercent = 50)
                ),
        ) {
            Text(
                text = "More", style = TextStyle(
                    fontSize = 18.sp,
                       
                    textAlign = TextAlign.Center,
                    color = Color.White
                ), modifier = Modifier.align(alignment = Alignment.Center)
            )

        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 20.dp)
        ) {

            Text(
                text = "Change Password", style = TextStyle(
                    fontSize = 14.sp,
                       
                    textAlign = TextAlign.Start,
                    color = Color.Black
                ), modifier = Modifier.align(alignment = Alignment.CenterVertically).weight(1f)
            )

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "",
                tint = Color.Gray,
                modifier = Modifier
                    .width(16.dp)
                    .height(16.dp)
                    .align(alignment = Alignment.CenterVertically)
            )

        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 20.dp)
        ) {

            Text(
                text = "FAQ's", style = TextStyle(
                    fontSize = 14.sp,
                       
                    textAlign = TextAlign.Start,
                    color = Color.Black
                ), modifier = Modifier.align(alignment = Alignment.CenterVertically).weight(1f)
            )

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "",
                tint = Color.Gray,
                modifier = Modifier
                    .width(16.dp)
                    .height(16.dp)
                    .align(alignment = Alignment.CenterVertically)
            )

        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 20.dp)
        ) {

            Text(
                text = "About App", style = TextStyle(
                    fontSize = 14.sp,
                       
                    textAlign = TextAlign.Start,
                    color = Color.Black
                ), modifier = Modifier.align(alignment = Alignment.CenterVertically).weight(1f)
            )

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "",
                tint = Color.Gray,
                modifier = Modifier
                    .width(16.dp)
                    .height(16.dp)
                    .align(alignment = Alignment.CenterVertically)
            )

        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 20.dp)
        ) {

            Text(
                text = "Terms & Conditions", style = TextStyle(
                    fontSize = 14.sp,
                       
                    textAlign = TextAlign.Start,
                    color = Color.Black
                ), modifier = Modifier.align(alignment = Alignment.CenterVertically).weight(1f)
            )

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "",
                tint = Color.Gray,
                modifier = Modifier
                    .width(16.dp)
                    .height(16.dp)
                    .align(alignment = Alignment.CenterVertically)
            )

        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 20.dp)
        ) {

            Text(
                text = "Privacy Policy", style = TextStyle(
                    fontSize = 14.sp,
                       
                    textAlign = TextAlign.Start,
                    color = Color.Black
                ), modifier = Modifier.align(alignment = Alignment.CenterVertically).weight(1f)
            )

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "",
                tint = Color.Gray,
                modifier = Modifier
                    .width(16.dp)
                    .height(16.dp)
                    .align(alignment = Alignment.CenterVertically)
            )

        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 20.dp)
        ) {

            Text(
                text = "Rate App", style = TextStyle(
                    fontSize = 14.sp,
                       
                    textAlign = TextAlign.Start,
                    color = Color.Black
                ), modifier = Modifier.align(alignment = Alignment.CenterVertically).weight(1f)
            )

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "",
                tint = Color.Gray,
                modifier = Modifier
                    .width(16.dp)
                    .height(16.dp)
                    .align(alignment = Alignment.CenterVertically)
            )

        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 20.dp)
        ) {

            Text(
                text = "Delete Account", style = TextStyle(
                    fontSize = 14.sp,
                       
                    textAlign = TextAlign.Start,
                    color = Color.Black
                ), modifier = Modifier.align(alignment = Alignment.CenterVertically).weight(1f)
            )

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "",
                tint = Color.Gray,
                modifier = Modifier
                    .width(16.dp)
                    .height(16.dp)
                    .align(alignment = Alignment.CenterVertically)
            )

        }
    }

}
