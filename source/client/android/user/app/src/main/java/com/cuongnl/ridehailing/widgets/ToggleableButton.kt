package com.cuongnl.ridehailing.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.cuongnl.ridehailing.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun ToggleableButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enable: Boolean = true,
    activeBackground: Color = colorResource(id = R.color.app_color),
    inactiveBackground: Color = colorResource(id = R.color.gray_600),
    activeTextColor: Color = colorResource(id = R.color.white),
    inactiveTextColor: Color = colorResource(id = R.color.white),
    textSize: TextUnit = 14.ssp,
    fontWeight: FontWeight = FontWeight.Medium,
    borderRadius: Dp = 8.sdp,
    contentPadding: PaddingValues = PaddingValues(vertical = 8.sdp),
    dimmedWhenDisable: Boolean = false,
    opacityWhenDisable: Float = 0.3f,
) {
    val buttonColor = if (enable) {
        activeBackground
    } else {
        inactiveBackground
    }

    TouchableOpacityButton(
        modifier = modifier
            .clip(RoundedCornerShape(borderRadius))
            .background(buttonColor)
            .padding(contentPadding),
        onClick = onClick,
        enable = enable,
        dimmedWhenDisable = dimmedWhenDisable,
        opacityWhenDisable = opacityWhenDisable,
    ) {
        AppText(
            modifier = Modifier.align(Alignment.Center),
            text = text,
            fontSize = textSize,
            color = if (enable) activeTextColor else inactiveTextColor,
            fontWeight = fontWeight
        )
    }
}