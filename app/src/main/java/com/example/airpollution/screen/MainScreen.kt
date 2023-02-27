package com.example.airpollution.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.airpollution.StoreDistrictName
import com.example.airpollution.common.composable.BasicText

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
            .background(Color.White)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            BasicText(
                text = districtName.value,
                modifier = Modifier.padding(15.dp)
            )
            BasicText(
                text = moveName.value,
                modifier = Modifier.padding(15.dp)
            )
        }
    }
}