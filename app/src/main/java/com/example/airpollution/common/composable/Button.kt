package com.example.airpollution.common.composable

import androidx.annotation.StringRes
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp

@Composable
fun BasicButton(
    @StringRes text: Int,
    modifier: Modifier,
    backgroundColor: Color,
    contentColor: Color,
    action: () -> Unit) {
    Button(
        onClick = action,
        modifier = modifier,
        elevation = null,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor)
    ) {
        Text(
            text = stringResource(text),
            fontSize = 17.sp
        )
    }
}