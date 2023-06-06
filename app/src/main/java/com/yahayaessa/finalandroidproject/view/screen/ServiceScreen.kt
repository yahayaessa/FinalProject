package com.yahayaessa.finalandroidproject.view.screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yahayaessa.finalandroidproject.R
import com.yahayaessa.finalandroidproject.model.PreferenceManager
import com.yahayaessa.finalandroidproject.view.items.ItemWorkServiceCard
import com.yahayaessa.finalandroidproject.viewModel.AllWorkViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ServiceScreen() {

    val viewModel: AllWorkViewModel = viewModel()
    val listModel = viewModel.listAllWorkLiveData
    val context = LocalContext.current



    viewModel.getAllWork()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xff346EDF), Color(0xff6FC8FB)),
                        startX = 0f,
                        endX = Float.POSITIVE_INFINITY
                    ), shape = RoundedCornerShape(bottomStartPercent = 50, bottomEndPercent = 50)
                ),

            ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(id = R.drawable.app_icon),
                    contentDescription = "app_icon",
                    modifier = Modifier
                        .width(80.dp)
                        .height(80.dp)
                )


                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 18.dp, bottom = 18.dp),
                    text = "Services",
                       
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp

                )

            }

        }


        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp)
        ) {
            itemsIndexed(items = listModel) { index, item ->
                ItemWorkServiceCard(model = item, onItemClick = {

                    val preferenceManager = PreferenceManager(context)
                    val userId = preferenceManager.getUserID()

                    if (userId!=-1) {
                        item.id?.let {
                            navigateToCreateOrderClass(
                                context = context,
                                it,
                                itemName = item.name.toString()
                            )
                        }
                    } else {
                        Toast.makeText(context, "Please Login to Create order", Toast.LENGTH_LONG)
                            .show()
                        navigateToLoginScreen(context)
                    }

                })

            }
        }

    }


}

private fun navigateToCreateOrderClass(context: Context, itemId: Int, itemName: String) {
    val intent = Intent(context, CreateOrderScreen::class.java).apply {
        putExtra("itemId", itemId)
        putExtra("itemName", itemName)
    }
    context.startActivity(intent)
}

private fun navigateToLoginScreen(context: Context) {
    val intent = Intent(context, LoginScreen::class.java).apply {

    }
    context.startActivity(intent)
}


