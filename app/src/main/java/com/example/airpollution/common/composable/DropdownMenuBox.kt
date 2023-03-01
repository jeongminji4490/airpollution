package com.example.airpollution.common.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.airpollution.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropDownMenuBox(
    labelText: String,
    dataStore: StoreDistrictName
) {
    var isDropDownMenuExpanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(labelText) }
    val districtName = dataStore.districtName.collectAsState(initial = "")

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
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = isDropDownMenuExpanded
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = TextStyle(
                color = Color.White
            ),
            shape = RoundedCornerShape(12.dp)
        )
        ExposedDropdownMenu(
            expanded = isDropDownMenuExpanded,
            onDismissRequest = {
                isDropDownMenuExpanded = false
            }
        ) {
            if (labelText == "지역 선택") {
                DropDownMenuItem(
                    list = DISTRICT_LIST,
                    state = isDropDownMenuExpanded,
                    isDistrictOrMoveName = 0,
                    onChangeIsDropDownMenuExpanded= { newValue -> isDropDownMenuExpanded = newValue },
                    onChangeSelectedOption = { newValue -> selectedOptionText = newValue },
                    dataStore = dataStore
                )
            }
            if (labelText == "권역 선택") {
                if (districtName.value == "서울") {
                    DropDownMenuItem(
                        list = SEOUL_MOVE_NAME,
                        state = isDropDownMenuExpanded,
                        isDistrictOrMoveName = 1,
                        onChangeIsDropDownMenuExpanded= { newValue -> isDropDownMenuExpanded = newValue },
                        onChangeSelectedOption = { newValue -> selectedOptionText = newValue },
                        dataStore = dataStore
                    )
                }
                if (districtName.value == "부산") {
                    DropDownMenuItem(
                        list = BUSAN_MOVE_LIST,
                        state = isDropDownMenuExpanded,
                        isDistrictOrMoveName = 1,
                        onChangeIsDropDownMenuExpanded= { newValue -> isDropDownMenuExpanded = newValue },
                        onChangeSelectedOption = { newValue -> selectedOptionText = newValue },
                        dataStore = dataStore
                    )
                }
                if (districtName.value == "대구") {
                    DropDownMenuItem(
                        list = DAEGU_MOVE_NAME,
                        state = isDropDownMenuExpanded,
                        isDistrictOrMoveName = 1,
                        onChangeIsDropDownMenuExpanded= { newValue -> isDropDownMenuExpanded = newValue },
                        onChangeSelectedOption = { newValue -> selectedOptionText = newValue },
                        dataStore = dataStore
                    )
                }
                if (districtName.value == "인천") {
                    DropDownMenuItem(
                        list = INCHEON_MOVE_LIST,
                        state = isDropDownMenuExpanded,
                        isDistrictOrMoveName = 1,
                        onChangeIsDropDownMenuExpanded= { newValue -> isDropDownMenuExpanded = newValue },
                        onChangeSelectedOption = { newValue -> selectedOptionText = newValue },
                        dataStore = dataStore
                    )
                }
                if (districtName.value == "광주") {
                    DropDownMenuItem(
                        list = GWANJU_MOVE_NAME,
                        state = isDropDownMenuExpanded,
                        isDistrictOrMoveName = 1,
                        onChangeIsDropDownMenuExpanded= { newValue -> isDropDownMenuExpanded = newValue },
                        onChangeSelectedOption = { newValue -> selectedOptionText = newValue },
                        dataStore = dataStore
                    )
                }
                if (districtName.value == "울산") {
                    DropDownMenuItem(
                        list = WOOLSAN_MOVE_NAME,
                        state = isDropDownMenuExpanded,
                        isDistrictOrMoveName = 1,
                        onChangeIsDropDownMenuExpanded= { newValue -> isDropDownMenuExpanded = newValue },
                        onChangeSelectedOption = { newValue -> selectedOptionText = newValue },
                        dataStore = dataStore
                    )
                }
                if (districtName.value == "세종") {
                    DropDownMenuItem(
                        list =  SEJONG_MOVE_NAME,
                        state = isDropDownMenuExpanded,
                        isDistrictOrMoveName = 1,
                        onChangeIsDropDownMenuExpanded= { newValue -> isDropDownMenuExpanded = newValue },
                        onChangeSelectedOption = { newValue -> selectedOptionText = newValue },
                        dataStore = dataStore
                    )
                }
                if (districtName.value == "대전") {
                    DropDownMenuItem(
                        list = DAEJEON_MOVE_LIST,
                        state = isDropDownMenuExpanded,
                        isDistrictOrMoveName = 1,
                        onChangeIsDropDownMenuExpanded= { newValue -> isDropDownMenuExpanded = newValue },
                        onChangeSelectedOption = { newValue -> selectedOptionText = newValue },
                        dataStore = dataStore
                    )
                }
                if (districtName.value == "경기") {
                    DropDownMenuItem(
                        list = GYEONGGI_MOVE_LIST,
                        state = isDropDownMenuExpanded,
                        isDistrictOrMoveName = 1,
                        onChangeIsDropDownMenuExpanded= { newValue -> isDropDownMenuExpanded = newValue },
                        onChangeSelectedOption = { newValue -> selectedOptionText = newValue },
                        dataStore = dataStore
                    )
                }
                if (districtName.value == "강원") {
                    DropDownMenuItem(
                        list = GANGWON_MOVE_LIST,
                        state = isDropDownMenuExpanded,
                        isDistrictOrMoveName = 1,
                        onChangeIsDropDownMenuExpanded= { newValue -> isDropDownMenuExpanded = newValue },
                        onChangeSelectedOption = { newValue -> selectedOptionText = newValue },
                        dataStore = dataStore
                    )
                }
                if (districtName.value == "충북") {
                    DropDownMenuItem(
                        list = CHUNGBUK_MOVE_LIST,
                        state = isDropDownMenuExpanded,
                        isDistrictOrMoveName = 1,
                        onChangeIsDropDownMenuExpanded= { newValue -> isDropDownMenuExpanded = newValue },
                        onChangeSelectedOption = { newValue -> selectedOptionText = newValue },
                        dataStore = dataStore
                    )
                }
                if (districtName.value == "충남") {
                    DropDownMenuItem(
                        list = CHUNGNAM_MOVE_LIST,
                        state = isDropDownMenuExpanded,
                        isDistrictOrMoveName = 1,
                        onChangeIsDropDownMenuExpanded= { newValue -> isDropDownMenuExpanded = newValue },
                        onChangeSelectedOption = { newValue -> selectedOptionText = newValue },
                        dataStore = dataStore
                    )
                }
                if (districtName.value == "전북") {
                    DropDownMenuItem(
                        list = JEONBUK_MOVE_LIST,
                        state = isDropDownMenuExpanded,
                        isDistrictOrMoveName = 1,
                        onChangeIsDropDownMenuExpanded= { newValue -> isDropDownMenuExpanded = newValue },
                        onChangeSelectedOption = { newValue -> selectedOptionText = newValue },
                        dataStore = dataStore
                    )
                }
                if (districtName.value == "전남") {
                    DropDownMenuItem(
                        list = JEONNAM_MOVE_LIST,
                        state = isDropDownMenuExpanded,
                        isDistrictOrMoveName = 1,
                        onChangeIsDropDownMenuExpanded= { newValue -> isDropDownMenuExpanded = newValue },
                        onChangeSelectedOption = { newValue -> selectedOptionText = newValue },
                        dataStore = dataStore
                    )
                }
                if (districtName.value == "경북") {
                    DropDownMenuItem(
                        list = GYEONGBUK_MOVE_LIST,
                        state = isDropDownMenuExpanded,
                        isDistrictOrMoveName = 1,
                        onChangeIsDropDownMenuExpanded= { newValue -> isDropDownMenuExpanded = newValue },
                        onChangeSelectedOption = { newValue -> selectedOptionText = newValue },
                        dataStore = dataStore
                    )
                }
                if (districtName.value == "경남") {
                    DropDownMenuItem(
                        list = GYEONGNAM_MOVE_LIST,
                        state = isDropDownMenuExpanded,
                        isDistrictOrMoveName = 1,
                        onChangeIsDropDownMenuExpanded= { newValue -> isDropDownMenuExpanded = newValue },
                        onChangeSelectedOption = { newValue -> selectedOptionText = newValue },
                        dataStore = dataStore
                    )
                }
            }
        }
    }
}

@Composable
fun DropDownMenuItem(
    list: List<String>,
    state: Boolean,
    isDistrictOrMoveName: Int,
    onChangeIsDropDownMenuExpanded: (Boolean) -> Unit,
    onChangeSelectedOption: (String) -> Unit,
    dataStore: StoreDistrictName
) {
    val scope = rememberCoroutineScope()

    if (isDistrictOrMoveName == 0) {
        list.forEach { selectedOption ->
            DropdownMenuItem(
                onClick = {
                    onChangeSelectedOption(selectedOption)
                    scope.launch {
                        dataStore.setDistrictName(selectedOption)
                    }
                    onChangeIsDropDownMenuExpanded(!state)
                }) {
                Text(
                    text = selectedOption
                )
            }
        }
    }else {
        list.forEach { selectedOption ->
            DropdownMenuItem(
                onClick = {
                    onChangeSelectedOption(selectedOption)
                    scope.launch {
                        dataStore.setMoveName(selectedOption)
                    }
                    onChangeIsDropDownMenuExpanded(!state)
                }) {
                Text(text = selectedOption)
            }
        }
    }
}