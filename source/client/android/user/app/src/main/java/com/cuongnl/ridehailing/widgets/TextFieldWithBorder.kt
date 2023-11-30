package com.cuongnl.ridehailing.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.utils.beVietNamFamily
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun TextFieldWithBorder(
    ref: MutableState<TextFieldValue> = mutableStateOf(
        TextFieldValue("")
    ),
    focusRequester: FocusRequester = FocusRequester(),
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    activeBorderColor: Int = R.color.app_color,
    inactiveBorderColor: Int = R.color.gray_300,
    textColor: Color = colorResource(id = R.color.black),
    placeholderColor: Color = colorResource(id = R.color.gray_600),
    textSize: TextUnit = 13.ssp,
    lineHeight: TextUnit = 24.ssp,
    width: Dp = 0.sdp,
    height: Dp = 40.sdp,
    contentPadding: PaddingValues = PaddingValues(horizontal = 10.sdp),
    borderRadius: Dp = 8.sdp,
    borderWidth: Dp = 1.dp,
    cursorColor : Color = colorResource(id = R.color.app_color),
    showClearButton : Boolean = true,
    clearButtonBackgroundColor : Color = colorResource(id = R.color.gray_400),
    clearIconColor : Color = colorResource(id = R.color.black),
    clearIconPadding : PaddingValues = PaddingValues(4.dp),
    clearButtonSize : Dp = 15.sdp,
) {

    var textFieldValue by remember {
        ref
    }

    val borderColorId = remember {
        mutableStateOf(inactiveBorderColor)
    }

    Row(
        modifier = if (width == 0.sdp) {
            modifier
                .clip(RoundedCornerShape(borderRadius))
                .fillMaxWidth()
        } else {
            modifier
                .clip(RoundedCornerShape(borderRadius))
                .width(width)
        }
            .height(height)
            .border(
                borderWidth,
                colorResource(id = borderColorId.value),
                RoundedCornerShape(borderRadius)
            )
            .padding(contentPadding),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BasicTextField(
            value = textFieldValue,
            onValueChange = {
                textFieldValue = it
                onValueChange(it)
            },
            modifier = Modifier
                .weight(1f)
                .onFocusChanged {
                    borderColorId.value =
                        if (it.isFocused)
                            activeBorderColor
                        else
                            inactiveBorderColor
                }
                .focusRequester(focusRequester),
            textStyle = TextStyle(
                fontSize = textSize,
                fontFamily = beVietNamFamily,
                color = textColor,
                lineHeight = lineHeight,
            ),
            decorationBox = { innerTextField ->
                if (textFieldValue.text.isEmpty() && placeholder.isNotEmpty()) {
                    AppText(
                        text = placeholder,
                        fontSize = textSize,
                        color = placeholderColor,
                        lineHeight = lineHeight,
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
                    .padding(start = contentPadding.calculateRightPadding(LayoutDirection.Ltr))
                    .clip(RoundedCornerShape(clearButtonSize))
                    .size(clearButtonSize)
                    .background(clearButtonBackgroundColor),
                onClick = {
                    textFieldValue = TextFieldValue("")
                    onValueChange(TextFieldValue(""))
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