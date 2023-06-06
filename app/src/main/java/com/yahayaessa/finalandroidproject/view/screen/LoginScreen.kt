package com.yahayaessa.finalandroidproject.view.screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yahayaessa.finalandroidproject.CustomButton
import com.yahayaessa.finalandroidproject.R
import com.yahayaessa.finalandroidproject.model.PreferenceManager
import com.yahayaessa.finalandroidproject.viewModel.LoginViewModel
import kotlinx.coroutines.delay


class LoginScreen : ComponentActivity() {


    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Scaffold() {
                val tabTitles = listOf("Service Provider", "Customer")
                val selectedTab = remember { mutableStateOf(0) }
                val loginViewModel: LoginViewModel = viewModel()

                val context = LocalContext.current
                val isLoggedIn by loginViewModel.isLoggedIn.collectAsState()
                val error by loginViewModel.error.collectAsState()
                val token by loginViewModel.theToken.collectAsState()
                val userId by loginViewModel.userId.collectAsState()
                val userPhone by loginViewModel.userPhone.collectAsState()


                val preferenceManager = PreferenceManager(context)




                if (isLoggedIn) {
                    preferenceManager.saveToken(token = token)
                    preferenceManager.saveUserId(userId = userId)
                    preferenceManager.saveUserPhone(userPhone = userPhone)

                    navigateToHomeScreen(context)
                }

                error?.let { errorMessage ->
                    // Display the error message
                    Toast.makeText(
                        context,
                        errorMessage,
                        Toast.LENGTH_LONG
                    ).show()

                }



                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(Color(0xff346EDF), Color(0xff6FC8FB)),
                                startX = 0f,
                                endX = Float.POSITIVE_INFINITY
                            )
                        )
                ) {


                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {


                        Image(
                            painter = painterResource(id = R.drawable.app_icon),
                            contentDescription = "background",
                            Modifier
                                .width(120.dp)
                                .height(120.dp)
                                .padding(top = 30.dp)
                        )

                        Card(
                            shape = RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp),
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 50.dp)
                        ) {
                            Column(modifier = Modifier.padding(15.dp)) {

                                TabRow(
                                    backgroundColor = Color.White, contentColor = Color.Gray,
                                    indicator = { tabPositions ->
                                        TabRowDefaults.Indicator(
                                            color = Color(0xff346EDF),
                                            modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab.value])
                                        )
                                    },
                                    selectedTabIndex = selectedTab.value,
                                ) {
                                    tabTitles.forEachIndexed { index, title ->
                                        Tab(selected = selectedTab.value == index,
                                            selectedContentColor = Color(0xff346EDF),
                                            unselectedContentColor = Color(0xff6FC8FB),
                                            onClick = { selectedTab.value = index },
                                            text = {
                                                Text(
                                                    text = title,
                                                    fontSize = 10.sp,
                                                )
                                            })
                                    }

                                }

                                when (selectedTab.value) {
                                    0 -> {


                                        var isVisible by remember { mutableStateOf(false) }

                                        LaunchedEffect(Unit) {
                                            // Start the timer when the composable is first composed
                                            isVisible = false

                                            // Delay for 2 seconds
                                            delay(0)

                                            // After the delay, hide the view
                                            isVisible = true
                                        }

                                        AnimatedVisibility(
                                            visible = isVisible,
                                            enter = slideInHorizontally(
                                                initialOffsetX = { 100 }, // Initial offset to the left
                                                animationSpec = TweenSpec(300) // Animation duration
                                            ),
                                        ) {
                                            ServiceProviderView()
                                        }


                                    }
                                    1 -> {

                                        var isVisible by remember { mutableStateOf(false) }

                                        LaunchedEffect(Unit) {
                                            // Start the timer when the composable is first composed
                                            isVisible = false

                                            // Delay for 2 seconds
                                            delay(0)

                                            // After the delay, hide the view
                                            isVisible = true
                                        }

                                        AnimatedVisibility(
                                            visible = isVisible,
                                            enter = slideInHorizontally(
                                                initialOffsetX = { -100 }, // Initial offset to the left
                                                animationSpec = TweenSpec(300) // Animation duration
                                            ),
                                        ) {
                                            LoginScreenView(loginViewModel = loginViewModel)

                                        }


                                    }
                                }

                            }


                        }
                    }
                }

            }

        }


    }

    override fun onBackPressed() {

    }
}


@Composable
fun LoginScreenView(loginViewModel: LoginViewModel) {


    val emailState = remember { mutableStateOf(TextFieldValue()) }
    val passwordState = remember { mutableStateOf(TextFieldValue()) }
    val rememberMeState = remember { mutableStateOf(false) }


    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = emailState.value,
            onValueChange = { emailState.value = it },

            label = {
                Text(
                    "Email",
                    Modifier.padding(2.dp),
                    fontSize = 10.sp,
                     

                    )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .background(Color.LightGray)
                .border(
                    BorderStroke(1.dp, Color(0xff346EDF)), shape = RoundedCornerShape(8.dp)
                ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFF3F3F3),
                cursorColor = Color.Blue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            textStyle = TextStyle(
                fontSize = 10.sp, textAlign = TextAlign.Start
            )


        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            label = {
                Text(
                    "Password",
                    Modifier.padding(2.dp),
                    Color.Gray,
                    fontSize = 10.sp,
                    )
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .border(
                    BorderStroke(1.dp, Color(0xff346EDF)), shape = RoundedCornerShape(8.dp)
                ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFF3F3F3),
                cursorColor = Color.Blue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            textStyle = TextStyle(
                fontSize = 10.sp,   textAlign = TextAlign.Start
            )

        )
        Spacer(modifier = Modifier.height(16.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Checkbox(
                checked = rememberMeState.value,
                onCheckedChange = {
                    rememberMeState.value = it
                },
                modifier = Modifier
                    .padding(0.dp)
                    .weight(1f),

                )


            Text(
                text = "Remember Me",
                color = Color.Black,
                style = MaterialTheme.typography.body1,
                fontSize = 12.sp,
                 


                )
            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )

            Text(
                text = "Forgot Password?",
                fontSize = 10.sp,
                color = Color.Black,
                 
            )

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 16.dp, start = 0.dp, end = 0.dp, bottom = 0.dp
                ), verticalAlignment = Alignment.CenterVertically

        ) {
            Column(
                modifier = Modifier.weight(1f), horizontalAlignment = Alignment.Start
            ) {

                Text(
                    text = "New Member? ",
                    color = Color.Black,
                    fontSize = 10.sp,
                     
                )

                val context = LocalContext.current


                TextButton(onClick = {
                    navigateToRegisterScreen(
                        context = context
                    )
                }) {
                    Text(
                        text = "Sign up",
                        fontSize = 16.sp,
                        color = Color.Blue,
                         
                        modifier = Modifier.padding(top = 1.dp),
                        textAlign = TextAlign.Start,

                        )
                }


            }





            CustomButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .height(64.dp),
                gradientColors = listOf(
                    Color(0xff346EDF), Color(0xff6FC8FB)
                ),
                onClick = {

                    val email = emailState.value.text
                    val password = passwordState.value.text


                    loginViewModel.login(
                        email = email,
                        password = password
                    )


                },
            ) {
                Text(
                    text = "Login",
                    fontSize = 16.sp,
                     
                    color = Color.White,
                    modifier = Modifier.padding(8.dp)
                )
            }


        }



        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,

            ) {

            val context = LocalContext.current


            TextButton(onClick = {
                navigateToHomeScreen(
                    context = context
                )
            }) {
                Text(
                    text = "Get Start Now",
                    fontSize = 18.sp,
                     
                    color = Color.Black,
                    modifier = Modifier.padding(8.dp)
                )

                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "icon",
                    tint = Color.Black
                )

            }


        }
    }


}


@Composable
fun ServiceProviderView() {
    val emailState = remember { mutableStateOf(TextFieldValue()) }
    val passwordState = remember { mutableStateOf(TextFieldValue()) }
    val rememberMeState = remember { mutableStateOf(false) }


    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = emailState.value,
            onValueChange = { emailState.value = it },

            label = {
                Text(
                    "Email",
                    Modifier.padding(2.dp),
                    Color.Gray,
                    fontSize = 10.sp,  

                    )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .background(Color.LightGray)
                .border(
                    BorderStroke(1.dp, Color(0xff346EDF)), shape = RoundedCornerShape(8.dp)
                ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFF3F3F3),
                cursorColor = Color.Blue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            textStyle = TextStyle(
                fontSize = 10.sp,   textAlign = TextAlign.Start
            )

        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            label = {
                Text(
                    "Password",
                    Modifier.padding(2.dp),
                    Color.Gray,
                    fontSize = 10.sp,  

                    )
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .border(
                    BorderStroke(1.dp, Color(0xff346EDF)), shape = RoundedCornerShape(8.dp)
                ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFF3F3F3),
                cursorColor = Color.Blue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            textStyle = TextStyle(
                fontSize = 10.sp,   textAlign = TextAlign.Start
            )
        )
        Spacer(modifier = Modifier.height(16.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Checkbox(
                checked = rememberMeState.value,
                onCheckedChange = { rememberMeState.value = it },
                modifier = Modifier,
            )


            Text(
                text = "Remember Me",
                 
                color = Color.Black,
                fontSize = 12.sp


            )
            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )

            Text(
                text = "Forgot Password?",
                fontSize = 10.sp,
                color = Color.Black,
                 
            )

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 16.dp, start = 0.dp, end = 0.dp, bottom = 0.dp
                ), verticalAlignment = Alignment.CenterVertically

        ) {
            Column(
                modifier = Modifier.weight(1f), horizontalAlignment = Alignment.Start
            ) {

                Text(
                    text = "New Member? ",
                    color = Color.Black,
                    fontSize = 10.sp,
                     
                )
                val context = LocalContext.current


                TextButton(onClick = { navigateToRegisterScreen(context = context) }) {
                    Text(
                        text = "Sign up",
                        fontSize = 10.sp,
                        color = Color.Blue,
                          modifier = Modifier.padding(top = 8.dp),
                        textAlign = TextAlign.Start,

                        )
                }


            }

            CustomButton(
                onClick = { /* Perform login action */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .height(64.dp),
                gradientColors = listOf(
                    Color(0xff346EDF), Color(0xff6FC8FB)
                )
            ) {
                Text(
                    text = "Login",
                    fontSize = 16.sp,
                     
                    color = Color.White,
                    modifier = Modifier.padding(8.dp)
                )
            }

        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,

            ) {
            val context = LocalContext.current


            TextButton(onClick = { navigateToHomeScreen(context = context) }) {
                Text(
                    text = "Get Start Now",
                    fontSize = 18.sp,
                     
                    color = Color.Black,
                    modifier = Modifier.padding(top = 1.dp)
                )

                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "icon",
                    tint = Color.Black
                )

            }


        }
    }

}

private fun navigateToHomeScreen(context: Context) {
    val intent = Intent(context, HomeScreen::class.java).apply {}
    context.startActivity(intent)
}

private fun navigateToRegisterScreen(context: Context) {
    val intent = Intent(context, RegisterScreen::class.java).apply {}
    context.startActivity(intent)
}


