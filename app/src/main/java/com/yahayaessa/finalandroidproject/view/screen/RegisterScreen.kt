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
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.yahayaessa.finalandroidproject.model.PreferenceManager
import com.yahayaessa.finalandroidproject.view.items.CustomDropdownMenu
import com.yahayaessa.finalandroidproject.viewModel.AllWorkViewModel
import com.yahayaessa.finalandroidproject.viewModel.RegisterViewModel
import kotlinx.coroutines.delay


class RegisterScreen : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Scaffold() {
                val registerViewModel: RegisterViewModel = viewModel()
                val isRegistered by registerViewModel.isRegistered.collectAsState()
                val error by registerViewModel.error.collectAsState()
                val token by registerViewModel.theToken.collectAsState()
                val userId by registerViewModel.userId.collectAsState()
                val userPhone by registerViewModel.userPhone.collectAsState()


                val context = LocalContext.current

                val preferenceManager = PreferenceManager(context)


                if (isRegistered) {
                    preferenceManager.saveToken(token = token)
                    preferenceManager.saveUserId(userId = userId)
                    preferenceManager.saveUserPhone(userPhone = userPhone)

                   preferenceManager.saveToken(token)
                    navigateTHomeScreen(context)
                }

                error?.let { errorMessage ->
                    // Display the error message
                    Toast.makeText(
                        context,
                        errorMessage,
                        Toast.LENGTH_LONG
                    ).show()

                }

                RegisterScreenView(registerViewModel)
            }
        }


    }

    override fun onBackPressed() {

    }
}

@Composable
fun RegisterScreenView(viewModel: RegisterViewModel) {

    val tabTitles = listOf("Service Provider", "Customer")
    val selectedTab = remember { mutableStateOf(0) }




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
            modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.Start
        ) {


            Box(modifier = Modifier.fillMaxWidth()) {

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 18.dp),
                    text = "Register",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp

                )

                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    val context = LocalContext.current


                    IconButton(onClick = { navigateToLoginScreen(context) }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White,
                            modifier = Modifier.padding(start = 6.dp, bottom = 14.dp, top = 20.dp)
                        )
                    }

                }
            }


            Card(
                shape = RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 30.dp)
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
                                text = { Text(text = title, fontSize = 10.sp) })
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
                                ServiceRegisterView()

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
                                CustomerRegisterView(viewModel)
                            }


                        }
                    }

                }


            }
        }
    }

}

@Composable
fun CustomerRegisterView(viewModel: RegisterViewModel) {


    val emailState = remember { mutableStateOf(TextFieldValue()) }
    val passwordState = remember { mutableStateOf(TextFieldValue()) }
    val fullNameState = remember { mutableStateOf(TextFieldValue()) }
    val phoneNumberState = remember { mutableStateOf(TextFieldValue()) }
    val rememberMeState = remember { mutableStateOf(false) }











    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())

    ) {

        TextField(
            value = fullNameState.value,
            onValueChange = { fullNameState.value = it },

            label = {
                Text(
                    "Full Name",
                    Modifier.padding(4.dp),
                    Color.Gray,
                    fontSize = 10.sp
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .background(Color.LightGray)
                .border(
                    BorderStroke(1.dp, Color(0xff346EDF)),
                    shape = RoundedCornerShape(8.dp)
                ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFF3F3F3),
                cursorColor = Color.Blue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            textStyle = TextStyle(
                fontSize = 10.sp,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Start
            )
        )


        TextField(
            value = emailState.value,
            onValueChange = { emailState.value = it },

            label = {
                Text(
                    "Email",
                    Modifier.padding(4.dp),
                    Color.Gray,
                    fontSize = 10.sp
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .background(Color.LightGray)
                .border(
                    BorderStroke(1.dp, Color(0xff346EDF)),
                    shape = RoundedCornerShape(8.dp)
                ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFF3F3F3),
                cursorColor = Color.Blue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            textStyle = TextStyle(
                fontSize = 10.sp,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Start
            )
        )


        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = phoneNumberState.value,
            onValueChange = { phoneNumberState.value = it },

            label = {
                Text(
                    "Phone Number",
                    Modifier.padding(4.dp),
                    Color.Gray,
                    fontSize = 10.sp
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .border(
                    BorderStroke(1.dp, Color(0xff346EDF)),
                    shape = RoundedCornerShape(8.dp)
                ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFF3F3F3),
                cursorColor = Color.Blue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            textStyle = TextStyle(
                fontSize = 10.sp,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Start
            )
        )


        TextField(
            value = passwordState.value,
            onValueChange = { passwordState.value = it },

            label = {
                Text(
                    "Password",
                    Modifier.padding(4.dp),
                    Color.Gray,
                    fontSize = 10.sp
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .background(Color.LightGray)
                .border(
                    BorderStroke(1.dp, Color(0xff346EDF)),
                    shape = RoundedCornerShape(8.dp)
                ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFF3F3F3),
                cursorColor = Color.Blue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,

                ),

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            textStyle = TextStyle(
                fontSize = 10.sp,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Start
            )
        )


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Checkbox(
                checked = rememberMeState.value,
                onCheckedChange = { rememberMeState.value = it },
            )



            Text(
                text = "I Read and Accept",
                color = Color.Black,
                fontSize = 8.sp, fontFamily = FontFamily.Serif, textAlign = TextAlign.Start

            )

            Text(
                text = "  Home Service Terms & Conditions",
                color = Color.Blue,
                fontSize = 6.sp, fontFamily = FontFamily.Serif, textAlign = TextAlign.Start
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
                    text = "Have Account? ",
                    color = Color.Black,
                    fontSize = 10.sp,
                    style = MaterialTheme.typography.body1,
                )
                val context = LocalContext.current

                TextButton(onClick = { navigateToLoginScreen(context) }) {
                    Text(
                        text = "Login",
                        fontSize = 12.sp,
                        color = Color.Blue,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(8.dp),
                        textAlign = TextAlign.Start,

                        )
                }


            }




            CustomButton(
                onClick = {
                    val name = fullNameState.value.text
                    val email = emailState.value.text
                    val password = passwordState.value.text
                    val phone = phoneNumberState.value.text

                    viewModel.register(name, email, password, phone)

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .height(64.dp),
                gradientColors = listOf(
                    Color(0xff346EDF),
                    Color(0xff6FC8FB)
                )
            ) {
                Text(
                    text = "Register", fontSize = 16.sp,
                    color = Color.White, modifier = Modifier.padding(8.dp)
                )
            }


        }


    }

}

@Composable
fun ServiceRegisterView() {


    val emailState = remember { mutableStateOf(TextFieldValue()) }
    val passwordState = remember { mutableStateOf(TextFieldValue()) }
    val fullNameState = remember { mutableStateOf(TextFieldValue()) }
    val phoneNumberState = remember { mutableStateOf(TextFieldValue()) }
    val rememberMeState = remember { mutableStateOf(false) }

    val viewModel: AllWorkViewModel = viewModel()
    val listModel = viewModel.listAllWorkLiveData

    val dropDownItems = mutableListOf<String>()
    viewModel.getAllWork()

    listModel.forEach { item ->
        dropDownItems.add(item.name.toString())
    }



    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())

    ) {

        TextField(
            value = fullNameState.value,
            onValueChange = { fullNameState.value = it },

            label = {
                Text(
                    "Full Name",
                    Modifier.padding(4.dp),
                    Color.Gray,
                    fontSize = 10.sp
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .background(Color.LightGray)
                .border(
                    BorderStroke(1.dp, Color(0xff346EDF)),
                    shape = RoundedCornerShape(8.dp)
                ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFF3F3F3),
                cursorColor = Color.Blue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            textStyle = TextStyle(
                fontSize = 10.sp,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Start
            )

        )


        TextField(
            value = emailState.value,
            onValueChange = { emailState.value = it },

            label = {
                Text(
                    "Email",
                    Modifier.padding(4.dp),
                    Color.Gray,
                    fontSize = 10.sp
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .background(Color.LightGray)
                .border(
                    BorderStroke(1.dp, Color(0xff346EDF)),
                    shape = RoundedCornerShape(8.dp)
                ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFF3F3F3),
                cursorColor = Color.Blue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            textStyle = TextStyle(
                fontSize = 10.sp,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Start
            )
        )


        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = phoneNumberState.value,
            onValueChange = { phoneNumberState.value = it },

            label = {
                Text(
                    "Phone Number",
                    Modifier.padding(4.dp),
                    Color.Gray,
                    fontSize = 10.sp
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .border(
                    BorderStroke(1.dp, Color(0xff346EDF)),
                    shape = RoundedCornerShape(8.dp)
                ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFF3F3F3),
                cursorColor = Color.Blue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            textStyle = TextStyle(
                fontSize = 10.sp,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Start
            )
        )


        TextField(
            value = passwordState.value,
            onValueChange = { passwordState.value = it },

            label = {
                Text(
                    "Password",
                    Modifier.padding(4.dp),
                    Color.Gray,
                    fontSize = 10.sp
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .background(Color.LightGray)
                .border(
                    BorderStroke(1.dp, Color(0xff346EDF)),
                    shape = RoundedCornerShape(8.dp)
                ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFF3F3F3),
                cursorColor = Color.Blue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,

                ),

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            textStyle = TextStyle(
                fontSize = 10.sp,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Start
            )
        )

        CustomDropdownMenu(dropDownItems)


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Checkbox(
                checked = rememberMeState.value,
                onCheckedChange = { rememberMeState.value = it },
            )


            Text(
                text = "I Read and Accept",
                color = Color.Black,
                fontSize = 8.sp, fontFamily = FontFamily.Serif, textAlign = TextAlign.Start

            )

            Text(
                text = "  Home Service Terms & Conditions",
                color = Color.Blue,
                fontSize = 6.sp, fontFamily = FontFamily.Serif, textAlign = TextAlign.Start
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
                    text = "Have Account? ",
                    color = Color.Black,
                    fontSize = 10.sp,
                    style = MaterialTheme.typography.body1,
                )
                val context = LocalContext.current


                TextButton(onClick = { navigateToLoginScreen(context) }) {
                    Text(
                        text = "Login",
                        fontSize = 12.sp,
                        color = Color.Blue,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(top = 6.dp),
                        textAlign = TextAlign.Start,

                        )
                }


            }
            val context = LocalContext.current


            CustomButton(
                onClick = {

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .height(64.dp),
                gradientColors = listOf(
                    Color(0xff346EDF),
                    Color(0xff6FC8FB)
                )
            ) {
                Text(
                    text = "Register", fontSize = 16.sp,
                    color = Color.White, modifier = Modifier.padding(8.dp)
                )
            }


        }
    }

}

private fun navigateToLoginScreen(context: Context) {
    val intent = Intent(context, LoginScreen::class.java).apply {}
    context.startActivity(intent)
}

private fun navigateTHomeScreen(context: Context) {
    val intent = Intent(context, HomeScreen::class.java).apply {}
    context.startActivity(intent)
}


