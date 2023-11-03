package com.cape.quicktask.core.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
fun TransparentHintTextField(
    text: String,
    hint: String,
    modifier: Modifier = Modifier,
    hintColor: Color = Color.DarkGray,
    isHintVisible: Boolean = true,
    onValueChange: (String) -> Unit = {},
    textStyle: TextStyle = TextStyle(),
    singleLine: Boolean = false,
    enabled: Boolean = true
) {
    Box(
        modifier = modifier
    ) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            textStyle = textStyle,
            enabled = enabled,
            modifier = Modifier
                .fillMaxWidth()
        )
        if (isHintVisible) {
            Text(text = hint, color = hintColor)
        }
    }
}