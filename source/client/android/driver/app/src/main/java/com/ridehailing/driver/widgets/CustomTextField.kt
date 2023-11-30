package com.ridehailing.driver.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.ridehailing.driver.R
import com.ridehailing.driver.utils.beVietNamFamily
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun CustomTextField(
    ref: MutableState<TextFieldValue> = mutableStateOf(
        TextFieldValue("")
    ),
    focusRequester: FocusRequester = remember {
        FocusRequester()
    },
    onValueChange: (TextFieldValue) -> Unit,
    onFocusChanged: (FocusState) -> Unit = {},
    modifier: Modifier = Modifier,
    placeholder: String = "",
    textColor: Color = colorResource(id = R.color.black),
    placeholderColor: Color = colorResource(id = R.color.gray_600),
    textSize: TextUnit = 13.ssp,
    fontWeight: FontWeight = FontWeight.Normal,
    lineHeight: TextUnit = 24.ssp,
    width: Dp = 0.sdp,
    cursorColor: Color = colorResource(id = R.color.app_color),
    showClearButton: Boolean = true,
    clearButtonBackgroundColor: Color = colorResource(id = R.color.gray_400),
    clearIconColor: Color = colorResource(id = R.color.black),
    clearIconPadding: PaddingValues = PaddingValues(4.dp),
    clearButtonSize: Dp = 15.sdp,
    selectAllOnFocus: Boolean = false,
) {
    var textFieldValue by remember {
        ref
    }

    val isFocused = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(isFocused.value) {

        val endRange = if (isFocused.value && selectAllOnFocus) textFieldValue.text.length else 0

        textFieldValue = textFieldValue.copy(
            selection = TextRange(
                start = 0,
                end = endRange
            )
        )
    }

    Row(
        modifier = if (width == 0.sdp) {
            modifier
                .fillMaxWidth()
        } else {
            modifier
                .width(width)
        },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BasicTextField(
            value = textFieldValue,
            onValueChange = {
                if (!it.text.equals(textFieldValue.text, false)) {
                    onValueChange(it)
                }
                textFieldValue = it
            },
            modifier = Modifier
                .weight(1f)
                .onFocusChanged {
                    onFocusChanged(it)
                    isFocused.value = it.hasFocus
                }
                .focusRequester(focusRequester),
            textStyle = TextStyle(
                fontSize = textSize,
                fontFamily = beVietNamFamily,
                color = textColor,
                lineHeight = lineHeight,
                fontWeight = fontWeight,
            ),
            decorationBox = { innerTextField ->
                if (textFieldValue.text.isEmpty() && placeholder.isNotEmpty()) {
                    AppText(
                        text = placeholder,
                        fontSize = textSize,
                        color = placeholderColor,
                        lineHeight = lineHeight,
                        fontWeight = fontWeight,
                    )
                }
                innerTextField()
            },
            cursorBrush = SolidColor(cursorColor),
            singleLine = true,
        )
        if (showClearButton && textFieldValue.text.isNotEmpty()) {
            TouchableOpacityButton(
                modifier = Modifier
                    .padding(start = 10.sdp)
                    .clip(RoundedCornerShape(clearButtonSize))
                    .size(clearButtonSize)
                    .background(clearButtonBackgroundColor),
                onClick = {
                    textFieldValue = TextFieldValue("")
                    onValueChange(TextFieldValue(""))
                    focusRequester.requestFocus()
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Clear,
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(clearIconPadding)
                        .fillMaxSize(),
                    tint = clearIconColor,
                )
            }
        }
    }
}