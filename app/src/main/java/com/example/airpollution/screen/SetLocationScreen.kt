package com.example.airpollution.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.airpollution.*
import com.example.airpollution.R
import com.example.airpollution.common.theme.white
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
            .background(white)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                openDropDownDistrictMenu = !openDropDownDistrictMenu
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(R.color.sky_blue),
                contentColor = Color.White
            )
        ) {
            Text(
                text = "지역 선택",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Default
            )
        }
        Spacer(Modifier.height(5.dp))
        if (openDropDownDistrictMenu)
            DropDownDistrictMenu()
        Spacer(Modifier.height(10.dp))
        Button(
            onClick = {
                openDropDownMoveMenu = !openDropDownMoveMenu
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(R.color.sky_blue),
                contentColor = Color.White
            )
        ) {
            Text(
                text = "권역 선택",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Default
            )
        }
        Spacer(Modifier.height(10.dp))
        if (openDropDownMoveMenu)
            DropDownMoveMenu()
        Spacer(Modifier.height(5.dp))
        Button(
            onClick = {
                scope.launch {
                    dataStore.setSettingValue(true)
                }
                openAndPopUp(MAIN_SCREEN, SET_LOCATION_SCREEN)
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White,
                contentColor = colorResource(R.color.sky_blue)
            )
        ) {
            Text(
                text = "등록",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Default
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropDownDistrictMenu() {
    var isDropDownMenuExpanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(DISTRICT_LIST[0]) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = StoreDistrictName(context)

    ExposedDropdownMenuBox(
        expanded = isDropDownMenuExpanded,
        onExpandedChange = {
            isDropDownMenuExpanded = !isDropDownMenuExpanded
        }
    ) {
        OutlinedTextField(
            readOnly = true,
            value = selectedOptionText,
            onValueChange = { },
            label = {
                Text(
                    text = "지역 선택",
                    color = Color.Black
                ) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = isDropDownMenuExpanded
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(R.color.sky_blue),
                unfocusedBorderColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = isDropDownMenuExpanded,
            onDismissRequest = {
                isDropDownMenuExpanded = false
            }
        ) {
            DISTRICT_LIST.forEach { selectedOption ->
                DropdownMenuItem(
                    onClick = {
                        selectedOptionText = selectedOption
                        scope.launch {
                            dataStore.setDistrictName(selectedOption)
                        }
                        isDropDownMenuExpanded = false
                    }
                ) {
                    Text(text = selectedOption)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropDownMoveMenu() {
    var isDropDownMenuExpanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf("") }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = StoreDistrictName(context)
    val districtName = dataStore.districtName.collectAsState(initial = "")

    ExposedDropdownMenuBox(
        expanded = isDropDownMenuExpanded,
        onExpandedChange = {
            isDropDownMenuExpanded = !isDropDownMenuExpanded
        }
    ) {
        OutlinedTextField(
            readOnly = true,
            value =  selectedOptionText,
            onValueChange = { },
            label = {
                Text(
                    text = "권역 선택",
                    color = Color.Black
                ) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = isDropDownMenuExpanded
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(R.color.sky_blue),
                unfocusedBorderColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = isDropDownMenuExpanded,
            onDismissRequest = {
                isDropDownMenuExpanded = false
            }
        ) {
            if (districtName.value == "서울") {
                DropdownMenuItem(
                    onClick = {
                        selectedOptionText = SEOUL_MOVE_NAME
                        scope.launch {
                            dataStore.setMoveName(SEOUL_MOVE_NAME)
                        }
                        isDropDownMenuExpanded = false
                    }) {
                    Text(text = SEOUL_MOVE_NAME)
                }
            }
            if (districtName.value == "부산") {
                BUSAN_MOVE_LIST.forEach { selectedOption ->
                    DropdownMenuItem(
                        onClick = {
                            selectedOptionText = selectedOption
                            scope.launch {
                                dataStore.setMoveName(selectedOption)
                            }
                            isDropDownMenuExpanded = false
                        }) {
                        Text(text = selectedOption)
                    }
                }
            }
            if (districtName.value == "대구") {
                DropdownMenuItem(
                    onClick = {
                        selectedOptionText = DAEGU_MOVE_NAME
                        scope.launch {
                            dataStore.setMoveName(DAEGU_MOVE_NAME)
                        }
                        isDropDownMenuExpanded = false
                    }) {
                    Text(text = DAEGU_MOVE_NAME)
                }
            }
            if (districtName.value == "인천") {
                INCHEON_MOVE_LIST.forEach { selectedOption ->
                    DropdownMenuItem(
                        onClick = {
                            selectedOptionText = selectedOption
                            scope.launch {
                                dataStore.setMoveName(selectedOption)
                            }
                            isDropDownMenuExpanded = false
                        }) {
                        Text(text = selectedOption)
                    }
                }
            }
            if (districtName.value == "광주") {
                DropdownMenuItem(
                    onClick = {
                        selectedOptionText = GWANJU_MOVE_NAME
                        scope.launch {
                            dataStore.setMoveName(GWANJU_MOVE_NAME)
                        }
                        isDropDownMenuExpanded = false
                    }) {
                    Text(text = SEOUL_MOVE_NAME)
                }
            }
            if (districtName.value == "울산") {
                DropdownMenuItem(
                    onClick = {
                        selectedOptionText = WOOLSAN_MOVE_NAME
                        scope.launch {
                            dataStore.setMoveName(WOOLSAN_MOVE_NAME)
                        }
                        isDropDownMenuExpanded = false
                    }) {
                    Text(text = WOOLSAN_MOVE_NAME)
                }
            }
            if (districtName.value == "세종") {
                DropdownMenuItem(
                    onClick = {
                        selectedOptionText = SEJONG_MOVE_NAME
                        scope.launch {
                            dataStore.setMoveName(SEJONG_MOVE_NAME)
                        }
                        isDropDownMenuExpanded = false
                    }) {
                    Text(text = SEJONG_MOVE_NAME)
                }
            }
            if (districtName.value == "대전") {
                DAEJEON_MOVE_LIST.forEach { selectedOption ->
                    DropdownMenuItem(
                        onClick = {
                            selectedOptionText = selectedOption
                            scope.launch {
                                dataStore.setMoveName(selectedOption)
                            }
                            isDropDownMenuExpanded = false
                        }) {
                        Text(text = selectedOption)
                    }
                }
            }
            if (districtName.value == "경기") {
                GYEONGGI_MOVE_LIST.forEach { selectedOption ->
                    DropdownMenuItem(
                        onClick = {
                            selectedOptionText = selectedOption
                            scope.launch {
                                dataStore.setMoveName(selectedOption)
                            }
                            isDropDownMenuExpanded = false
                        }) {
                        Text(text = selectedOption)
                    }
                }
            }
            if (districtName.value == "강원") {
                GANGWON_MOVE_LIST.forEach { selectedOption ->
                    DropdownMenuItem(
                        onClick = {
                            selectedOptionText = selectedOption
                            scope.launch {
                                dataStore.setMoveName(selectedOption)
                            }
                            isDropDownMenuExpanded = false
                        }) {
                        Text(text = selectedOption)
                    }
                }
            }
            if (districtName.value == "충북") {
                CHUNGBUK_MOVE_LIST.forEach { selectedOption ->
                    DropdownMenuItem(
                        onClick = {
                            selectedOptionText = selectedOption
                            scope.launch {
                                dataStore.setMoveName(selectedOption)
                            }
                            isDropDownMenuExpanded = false
                        }) {
                        Text(text = selectedOption)
                    }
                }
            }
            if (districtName.value == "충남") {
                CHUNGNAM_MOVE_LIST.forEach { selectedOption ->
                    DropdownMenuItem(
                        onClick = {
                            selectedOptionText = selectedOption
                            scope.launch {
                                dataStore.setMoveName(selectedOption)
                            }
                            isDropDownMenuExpanded = false
                        }) {
                        Text(text = selectedOption)
                    }
                }
            }
            if (districtName.value == "전북") {
                JEONBUK_MOVE_LIST.forEach { selectedOption ->
                    DropdownMenuItem(
                        onClick = {
                            selectedOptionText = selectedOption
                            scope.launch {
                                dataStore.setMoveName(selectedOption)
                            }
                            isDropDownMenuExpanded = false
                        }) {
                        Text(text = selectedOption)
                    }
                }
            }
            if (districtName.value == "전남") {
                JEONNAM_MOVE_LIST.forEach { selectedOption ->
                    DropdownMenuItem(
                        onClick = {
                            selectedOptionText = selectedOption
                            scope.launch {
                                dataStore.setMoveName(selectedOption)
                            }
                            isDropDownMenuExpanded = false
                        }) {
                        Text(text = selectedOption)
                    }
                }
            }
            if (districtName.value == "경북") {
                GYEONGBUK_MOVE_LIST.forEach { selectedOption ->
                    DropdownMenuItem(
                        onClick = {
                            selectedOptionText = selectedOption
                            scope.launch {
                                dataStore.setMoveName(selectedOption)
                            }
                            isDropDownMenuExpanded = false
                        }) {
                        Text(text = selectedOption)
                    }
                }
            }
            if (districtName.value == "경남") {
                GYEONGNAM_MOVE_LIST.forEach { selectedOption ->
                    DropdownMenuItem(
                        onClick = {
                            selectedOptionText = selectedOption
                            scope.launch {
                                dataStore.setMoveName(selectedOption)
                            }
                            isDropDownMenuExpanded = false
                        }) {
                        Text(text = selectedOption)
                    }
                }
            }
        }
    }
}