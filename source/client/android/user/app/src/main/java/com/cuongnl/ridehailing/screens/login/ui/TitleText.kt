package com.cuongnl.ridehailing.screens.login.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.widgets.AppText

@Composable
fun TitleText() {
    AppText(
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    fontSize = 14.sp
                )
            ) {
                append(stringResource(id = R.string.log_in_text_title_part_1))
            }
            append(" ")
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    fontSize = 14.sp
                )
            ) {
                append(stringResource(id = R.string.log_in_text_title_part_2))
            }
            append(" ")
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    fontSize = 14.sp
                )
            ) {
                append(stringResource(id = R.string.log_in_text_title_part_3))
            }
        },
    )
}
