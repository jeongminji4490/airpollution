package com.example.airpollution.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.airpollution.*
import com.example.airpollution.R
import com.example.airpollution.common.composable.BasicButton
import com.example.airpollution.common.composable.DropDownMenuBox
import kotlinx.coroutines.launch

@Composable
fun SetLocationScreen(
    openAndPopUp: (String, String) -> Unit
) {
    var openDropDownDistrictMenu by remember { mutableStateOf(false) }
    var openDropDownMoveMenu by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = StoreDistrictName(context)

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        BasicButton(
            text = "지역 선택",
            backgroundColor = colorResource(R.color.sky_blue),
            contentColor = Color.White
        ) {
            openDropDownDistrictMenu = !openDropDownDistrictMenu
        }

        Spacer(Modifier.height(5.dp))

        if (openDropDownDistrictMenu)
            DropDownMenuBox(
                labelText = "지역 선택",
                dataStore = dataStore)

        Spacer(Modifier.height(10.dp))

        BasicButton(
            text = "권역 선택",
            backgroundColor = colorResource(R.color.sky_blue),
            contentColor = Color.White
        ) {
            openDropDownMoveMenu = !openDropDownMoveMenu
        }

        Spacer(Modifier.height(10.dp))

        if (openDropDownMoveMenu)
            DropDownMenuBox(
                labelText = "권역 선택",
                dataStore = dataStore)

        Spacer(Modifier.height(5.dp))

        BasicButton(
            text = "등록",
            backgroundColor = Color.White,
            contentColor = colorResource(R.color.sky_blue)
        ) {
            scope.launch {
                dataStore.setSettingValue(true)
            }
            openAndPopUp(MAIN_SCREEN, SET_LOCATION_SCREEN)
        }
    }
}