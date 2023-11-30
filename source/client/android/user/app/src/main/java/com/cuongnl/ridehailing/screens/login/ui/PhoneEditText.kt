package com.cuongnl.ridehailing.screens.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.utils.CheckerUtils
import com.cuongnl.ridehailing.utils.beVietNamFamily
import com.cuongnl.ridehailing.viewmodel.CountryCodeBottomSheetViewModel
import com.cuongnl.ridehailing.viewmodel.CountryCodeSelectedViewModel
import com.cuongnl.ridehailing.viewmodel.TextEnteredViewModel
import com.cuongnl.ridehailing.widgets.AppText
import com.cuongnl.ridehailing.widgets.TouchableOpacityButton
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun PhoneEditText() {
    Row(
        modifier = Modifier
            .padding(top = 25.dp)
            .clip(RoundedCornerShape(8.sdp))
            .fillMaxWidth()
            .height(40.sdp)
            .border(
                1.sdp,
                colorResource(id = R.color.app_color),
                RoundedCornerShape(8.sdp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        PhoneCode()
        PhoneTextField()
    }
}

@Composable
private fun PhoneCode(
    countryCodeSelectedViewModel: CountryCodeSelectedViewModel = viewModel(),
    bottomSheetViewModel: CountryCodeBottomSheetViewModel = viewModel()
) {
    TouchableOpacityButton(
        onClick = {
            bottomSheetViewModel.setBottomSheetVisible(true)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .background(colorResource(id = R.color.gray_100))
                .padding(start = 10.sdp, end = 4.sdp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .height(18.sdp),
                painter = painterResource(countryCodeSelectedViewModel.currentCountryCode.value.countryFlag),
                contentDescription = null
            )
            AppText(
                modifier = Modifier
                    .padding(start = 3.sdp, bottom = 4.sdp),
                text = countryCodeSelectedViewModel.currentCountryCode.value.phoneCode,
                color = Color.Black,
                fontSize = 13.ssp,
                fontWeight = FontWeight.SemiBold,
            )
            Icon(
                Icons.Outlined.KeyboardArrowDown,
                contentDescription = null
            )
        }
    }
}

@Composable
private fun PhoneTextField(
    countryCodeSelectedViewModel: CountryCodeSelectedViewModel = viewModel(),
    textEnteredViewModel: TextEnteredViewModel = viewModel()
) {

    var textFieldValue by remember {
        mutableStateOf(
            TextFieldValue(
                textEnteredViewModel.text.value,
                TextRange(
                    countryCodeSelectedViewModel.currentCountryCode.value.minLength,
                    countryCodeSelectedViewModel.currentCountryCode.value.maxLength
                )
            )
        )
    }

    BasicTextField(
        value = textFieldValue,
        onValueChange = {
            val text = it.text
            if (CheckerUtils.isOnlyNumbers(text)) {
                textFieldValue = it
                textEnteredViewModel.setText(text)
            }
        },
        modifier = Modifier
            .padding(bottom = 4.sdp, start = 8.sdp, end = 8.sdp)
            .fillMaxWidth(),
        textStyle = TextStyle(
            fontSize = 13.ssp,
            fontFamily = beVietNamFamily,
            color = colorResource(R.color.black),
            lineHeight = 24.ssp,
            fontWeight = FontWeight.SemiBold
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        decorationBox = { innerTextField ->
            if (textFieldValue.text.isEmpty()) {
                AppText(
                    text = stringResource(id = R.string.your_number_hint),
                    fontSize = 13.ssp,
                    color = colorResource(R.color.gray_600),
                    lineHeight = 24.ssp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            innerTextField()
        }
    )
}

@Preview
@Composable
fun Preview() {
    PhoneEditText()
}