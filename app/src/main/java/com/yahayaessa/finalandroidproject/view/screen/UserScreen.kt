package com.yahayaessa.finalandroidproject.view.screen

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yahayaessa.finalandroidproject.R
import com.yahayaessa.finalandroidproject.model.PreferenceManager

@Composable
fun UserScreen() {

    Box(
        modifier = Modifier.background(
            brush = Brush.horizontalGradient(
                colors = listOf(Color(0xff346EDF), Color(0xff6FC8FB)),
                startX = 0f,
                endX = Float.POSITIVE_INFINITY
            )
        )
    ) {
        val overlayBoxHeight = 50.dp
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 120.dp), shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)
        ) {


            Column(modifier = Modifier.padding(top = 50.dp)) {


                Text(
                    text = "Mohammed Hamad Mushtaha",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily.Monospace,
                        textAlign = TextAlign.Start,
                        color = Color.Black
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .padding(bottom = 4.dp, top = 4.dp)
                )

                Text(
                    text = "Gaza.Palestine 970",
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontFamily = FontFamily.Monospace,
                        textAlign = TextAlign.Start,
                        color = Color(0xAE6D6D6D)
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally).
                            padding(bottom = 16.dp)
                )
                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                        .fillMaxWidth()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(Color(0x80346EDF), Color(0x806FC8FB)),
                                startX = 0f,
                                endX = Float.POSITIVE_INFINITY
                            )
                        )
                )

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 20.dp)
                ) {

                    Text(
                        text = "Language",
                        style = TextStyle(
                            fontSize = 14.sp,
                               
                            textAlign = TextAlign.Start,
                            color = Color.Black
                        ),
                        modifier = Modifier
                            .align(alignment = Alignment.CenterVertically)
                            .weight(1f)
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
                        text = "My Rates",
                        style = TextStyle(
                            fontSize = 14.sp,
                               
                            textAlign = TextAlign.Start,
                            color = Color.Black
                        ),
                        modifier = Modifier
                            .align(alignment = Alignment.CenterVertically)
                            .weight(1f)
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
                        text = "Contact us",
                        style = TextStyle(
                            fontSize = 14.sp,
                               
                            textAlign = TextAlign.Start,
                            color = Color.Black
                        ),
                        modifier = Modifier
                            .align(alignment = Alignment.CenterVertically)
                            .weight(1f)
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
                        .padding(start = 16.dp, end = 16.dp, top = 20.dp, bottom = 16.dp)
                ) {

                    Text(
                        text = "Share App",
                        style = TextStyle(
                            fontSize = 14.sp,
                               
                            textAlign = TextAlign.Start,
                            color = Color.Black
                        ),
                        modifier = Modifier
                            .align(alignment = Alignment.CenterVertically)
                            .weight(1f)
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

                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                        .fillMaxWidth()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(Color(0x80346EDF), Color(0x806FC8FB)),
                                startX = 0f,
                                endX = Float.POSITIVE_INFINITY
                            )
                        )
                )


                val context = LocalContext.current
                val preferenceManager = PreferenceManager(context)
                TextButton(
                    onClick = {
                        preferenceManager.deleteToken()
                        preferenceManager.deleteUserId()
                        preferenceManager.deleteUserUserPhone()
                        navigateToLoginScreen(context)
                    },
                    Modifier
                        .padding(top = 32.dp)
                        .align(alignment = Alignment.CenterHorizontally)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "SIGN OUT", fontSize = 18.sp,
                           
                        color = Color.Black, modifier = Modifier.padding(top = 1.dp)
                    )

                    Icon(
                        imageVector = Icons.Default.ExitToApp,
                        contentDescription = "icon",
                        tint = Color.Red
                    )


                }


            }
        }
        Image(
            painterResource(id = R.drawable.user_avater_icon),
            contentDescription = "",
            modifier = Modifier
                .align(TopCenter)
                .offset(x = 0.dp, y = overlayBoxHeight)
                .width(120.dp)
                .height(120.dp)
                .clip(CircleShape)
                .border(
                    BorderStroke(4.dp, Color(0xFF43A047)),
                    CircleShape
                )
                .padding(4.dp)
                .clip(CircleShape),
            contentScale = ContentScale.FillBounds
        )
    }


}

private fun navigateToLoginScreen(context: Context) {
    val intent = Intent(context, LoginScreen::class.java).apply {}
    context.startActivity(intent)
}






