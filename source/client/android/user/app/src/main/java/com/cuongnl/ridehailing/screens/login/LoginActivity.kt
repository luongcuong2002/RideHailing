package com.cuongnl.ridehailing.screens.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.activitybehavior.ILoginActivityBehavior
import com.cuongnl.ridehailing.callbacks.api.UserCheckCallback
import com.cuongnl.ridehailing.core.BaseActivity
import com.cuongnl.ridehailing.enums.OtpAuthType
import com.cuongnl.ridehailing.models.api.ScalarsBooleanResponse
import com.cuongnl.ridehailing.screens.login.ui.BannerImage
import com.cuongnl.ridehailing.screens.login.ui.ContinueButton
import com.cuongnl.ridehailing.screens.login.ui.PhoneCodeBottomSheet
import com.cuongnl.ridehailing.screens.login.ui.PhoneEditText
import com.cuongnl.ridehailing.screens.login.ui.PhoneNumberInvalidText
import com.cuongnl.ridehailing.screens.login.ui.PolicyText
import com.cuongnl.ridehailing.screens.login.ui.TitleText
import com.cuongnl.ridehailing.screens.otpverification.OtpVerificationActivity
import com.cuongnl.ridehailing.screens.passwordverification.PasswordVerificationActivity
import com.cuongnl.ridehailing.theme.AppTheme
import com.cuongnl.ridehailing.utils.Constant
import com.cuongnl.ridehailing.utils.FormatterUtils
import com.cuongnl.ridehailing.viewmodel.CountryCodeSelectedViewModel
import com.cuongnl.ridehailing.viewmodel.LoaderViewModel
import com.cuongnl.ridehailing.viewmodel.TextEnteredViewModel
import com.cuongnl.ridehailing.viewmodel.apiservice.AuthServiceViewModel
import com.cuongnl.ridehailing.widgets.FullScreenLoader
import ir.kaaveh.sdpcompose.sdp
import retrofit2.Call


val LocalActivityBehavior =
    staticCompositionLocalOf<ILoginActivityBehavior> { error("No LocalActivityActionsClass provided") }

class LoginScreen : BaseActivity(), ILoginActivityBehavior {

    private lateinit var authServiceViewModel: AuthServiceViewModel
    private lateinit var textEnteredViewModel: TextEnteredViewModel
    private lateinit var countryCodeSelectedViewModel: CountryCodeSelectedViewModel
    private lateinit var loaderViewModel: LoaderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModel()

        setContent {
            CompositionLocalProvider(LocalActivityBehavior provides this) {
                Screen()
            }
        }
    }

    private fun setupViewModel() {
        authServiceViewModel = ViewModelProvider(this)[AuthServiceViewModel::class.java]
        textEnteredViewModel = ViewModelProvider(this)[TextEnteredViewModel::class.java]
        countryCodeSelectedViewModel =
            ViewModelProvider(this)[CountryCodeSelectedViewModel::class.java]
        loaderViewModel = ViewModelProvider(this)[LoaderViewModel::class.java]
    }

    override fun isInvalidTextVisible(): Boolean {
        val textEnteredLength = textEnteredViewModel.text.value.length

        return !canClickContinueButton() && textEnteredLength != 0
    }

    override fun canClickContinueButton(): Boolean {
        val textEnteredLength = textEnteredViewModel.text.value.length
        val maxLength = countryCodeSelectedViewModel.currentCountryCode.value.maxLength
        val minLength = countryCodeSelectedViewModel.currentCountryCode.value.minLength

        return textEnteredLength in minLength..maxLength
    }

    override fun openPolicy() {
        val uri = Uri.parse(Constant.POLICY_URL)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    override fun onContinueButtonClicked() {
        val phoneNumber = textEnteredViewModel.text.value
        val countryCode = countryCodeSelectedViewModel.currentCountryCode.value.countryCode

        val internationalPhoneNumber =
            FormatterUtils.formatPhoneNumberToInternationalFormation(phoneNumber, countryCode)

        loaderViewModel.setLoading(true)

        authServiceViewModel.checkExistingUser(internationalPhoneNumber, object : UserCheckCallback<ScalarsBooleanResponse> {

            override fun onUserExisting() {
                val intent = Intent(this@LoginScreen, PasswordVerificationActivity::class.java)
                intent.putExtra(Constant.BUNDLE_INTERNATIONAL_PHONE_NUMBER, internationalPhoneNumber)
                startActivity(intent)
            }

            override fun onUserNotExisting() {
                val intent = Intent(this@LoginScreen, OtpVerificationActivity::class.java)
                intent.putExtra(Constant.BUNDLE_OTP_AUTH_TYPE, OtpAuthType.SIGN_UP)
                intent.putExtra(Constant.BUNDLE_INTERNATIONAL_PHONE_NUMBER, internationalPhoneNumber)
                startActivity(intent)
            }

            override fun onFailure(call: Call<ScalarsBooleanResponse>, t: Throwable) {
                Toast.makeText(
                    this@LoginScreen,
                    getString(R.string.cannot_connect_to_server),
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onFinish() {
                loaderViewModel.setLoading(false)
            }
        })
    }
}

@Composable
private fun Screen() {
    AppTheme {
        FullScreenLoader {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                BannerImage()
                Column(
                    modifier = Modifier
                        .padding(horizontal = 15.sdp)
                        .imePadding()
                ) {
                    TitleText()
                    PhoneEditText()
                    PhoneNumberInvalidText()

                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        ContinueButton()
                        PolicyText()
                    }

                    PhoneCodeBottomSheet()
                }
            }
        }
    }
}