package com.example.airpollution.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.airpollution.StoreDistrictName
import com.example.airpollution.common.theme.white

@Composable
fun MainScreen(
    openAndPopUp: (String, String) -> Unit
) {
    val context = LocalContext.current
    val dataStore = StoreDistrictName(context)
    val districtName = dataStore.districtName.collectAsState(initial = "")
    val moveName = dataStore.moveName.collectAsState(initial = "")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(white)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = districtName.value,
                modifier = Modifier
                    .padding(15.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Default
            )
            Text(
                text = moveName.value,
                modifier = Modifier
                    .padding(15.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Default
            )
        }
    }
}