package com.example.airpollution.common.composable

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun BasicText(
    text: String,
    modifier: Modifier
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = 17.sp,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center,
        fontFamily = fontFamily,
        color = Color.White
    )
}