package com.example.airpollution

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.airpollution.common.composable.BasicText
import com.example.airpollution.common.composable.DropDownMenuBox
import com.example.airpollution.common.composable.fontFamily

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Main()
        }
    }

    @Composable
    fun Main() {
        val context = LocalContext.current
        val dataStore = StoreDistrictName(context)

        val districtName = dataStore.districtName.collectAsState(initial = "").value
        val moveName = dataStore.moveName.collectAsState(initial = "").value

        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.sky_blue))
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.balloon),
                contentDescription = "main image"
            )
            Text(
                text = "미세먼지 알리미",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                fontSize = 29.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontFamily = fontFamily,
                color = Color.White
            )

            Spacer(Modifier.height(5.dp))

            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                BasicText(
                    text = "지역 선택",
                    modifier = Modifier
                        .padding(15.dp)
                )
                DropDownMenuBox(
                    labelText = "지역 선택",
                    dataStore = dataStore
                )
            }

            Spacer(Modifier.height(10.dp))

            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                BasicText(
                    text = "권역 선택",
                    modifier = Modifier
                        .padding(15.dp)
                )
                DropDownMenuBox(
                    labelText = "권역 선택",
                    dataStore = dataStore)
            }

            Spacer(Modifier.height(20.dp))

            Divider(
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .width(1.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                BasicText(
                    text = districtName,
                    modifier = Modifier.padding(15.dp)
                )
                Divider(
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                )
                BasicText(
                    text = moveName,
                    modifier = Modifier.padding(15.dp)
                )
            }
            Divider(
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .width(1.dp)
            )
        }
    }
}
