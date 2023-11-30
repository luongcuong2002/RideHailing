import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ridehailing.driver.R
import com.ridehailing.driver.utils.CheckerUtils
import com.ridehailing.driver.widgets.AppText
import ir.kaaveh.sdpcompose.sdp

@Composable
fun OtpInputField(
    modifier: Modifier = Modifier,
    otpText: String = "",
    otpCount: Int = 6,
    isTextVisible: Boolean = true,
    onOtpTextChange: (String, Boolean) -> Unit
) {
    LaunchedEffect(Unit) {
        if (otpText.length > otpCount) {
            throw IllegalArgumentException("Otp text value must not have more than otpCount: $otpCount characters")
        }
    }

    var textFieldValue by remember {
        mutableStateOf(
            TextFieldValue(otpText, selection = TextRange(otpText.length))
        )
    }

    BasicTextField(
        modifier = modifier,
        value = textFieldValue,
        onValueChange = {
            if (it.text.length <= otpCount && CheckerUtils.isOnlyNumbers(it.text)) {
                textFieldValue = it
                onOtpTextChange.invoke(it.text, it.text.length == otpCount)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        decorationBox = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.sdp, Alignment.CenterHorizontally)
            ) {
                repeat(otpCount) { index ->
                    CharView(
                        index = index,
                        text = textFieldValue.text,
                        isTextVisible = isTextVisible,
                    )
                }
            }
        }
    )
}

@Composable
private fun RowScope.CharView(
    index: Int,
    text: String,
    isTextVisible: Boolean
) {
    val isFocused = text.length == index
    val char = when {
        index == text.length -> ""
        index > text.length -> ""
        else -> text[index].toString()
    }

    val backgroundColor = if (isFocused) {
        colorResource(id = R.color.amber_50)
    } else {
        colorResource(id = R.color.gray_200)
    }

    Box(
        modifier = Modifier
            .weight(1f)
            .aspectRatio(0.75f)
            .clip(RoundedCornerShape(25))
            .background(backgroundColor)
    ) {
        if (char.isNotEmpty() && !isTextVisible) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(100))
                    .size(8.sdp)
                    .background(Color.Black)
            )
        } else {
            AppText(
                modifier = Modifier
                    .align(Alignment.Center),
                text = char,
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
            )
        }
    }
}

@Preview
@Composable
fun Preview() {
    OtpInputField(otpText = "", onOtpTextChange = { _, _ -> })
}