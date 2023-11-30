package com.cuongnl.ridehailing.screens.newusercreation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.activitybehavior.INewUserCreationActivityBehavior
import com.cuongnl.ridehailing.callbacks.api.SimpleApiCallback
import com.cuongnl.ridehailing.core.BaseActivity
import com.cuongnl.ridehailing.models.api.RegisterRequest
import com.cuongnl.ridehailing.models.api.ScalarsBooleanResponse
import com.cuongnl.ridehailing.screens.newusercreation.ui.PasswordCreationBottomSheet
import com.cuongnl.ridehailing.screens.newusercreation.ui.UserEmailTextField
import com.cuongnl.ridehailing.screens.newusercreation.ui.UserInfoCreationDescription
import com.cuongnl.ridehailing.screens.newusercreation.ui.UserInfoCreationTitle
import com.cuongnl.ridehailing.screens.newusercreation.ui.UserNameContinueButton
import com.cuongnl.ridehailing.screens.newusercreation.ui.UserNameTextField
import com.cuongnl.ridehailing.theme.AppTheme
import com.cuongnl.ridehailing.utils.Constant
import com.cuongnl.ridehailing.viewmodel.apiservice.AuthServiceViewModel
import com.cuongnl.ridehailing.viewmodel.LoaderViewModel
import com.cuongnl.ridehailing.viewmodel.UserInfoCreationViewModel
import com.cuongnl.ridehailing.widgets.BackButton
import com.cuongnl.ridehailing.widgets.FullScreenLoader
import ir.kaaveh.sdpcompose.sdp
import retrofit2.Call
import retrofit2.Response

val LocalActivityBehavior =
    staticCompositionLocalOf<INewUserCreationActivityBehavior> { error("No LocalActivityActionsClass provided") }

class NewUserCreationActivity : BaseActivity(), INewUserCreationActivityBehavior {

    private lateinit var loaderViewModel: LoaderViewModel
    private lateinit var authServiceViewModel: AuthServiceViewModel
    private lateinit var userInfoCreationViewModel: UserInfoCreationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModel()

        setContent {
            CompositionLocalProvider(LocalActivityBehavior provides this) {
                Screen()
            }
        }
    }

    override fun popActivity() {
        finish()
    }

    override fun register() {

        val phoneNumber = intent.getStringExtra(Constant.BUNDLE_INTERNATIONAL_PHONE_NUMBER)
        val userName = userInfoCreationViewModel.getUserName()
        val email = userInfoCreationViewModel.getUserEmail()
        val password = userInfoCreationViewModel.getUserPassword()

        val registerRequest = RegisterRequest(
            phoneNumber = phoneNumber!!,
            userName = userName,
            email = email.ifEmpty { null },
            password = password
        )

        loaderViewModel.setLoading(true)

        authServiceViewModel.register(
            registerRequest,
            object : SimpleApiCallback<ScalarsBooleanResponse> {
                override fun onFinish() {
                    loaderViewModel.setLoading(false)
                }

                override fun onSuccess(
                    call: Call<ScalarsBooleanResponse>,
                    response: Response<ScalarsBooleanResponse>
                ) {
                    popActivity()
                    Toast.makeText(
                        this@NewUserCreationActivity,
                        getString(R.string.register_success),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onFailure(call: Call<ScalarsBooleanResponse>, t: Throwable) {
                    Toast.makeText(
                        this@NewUserCreationActivity,
                        getString(R.string.something_went_wrong),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onError(
                    call: Call<ScalarsBooleanResponse>,
                    response: Response<ScalarsBooleanResponse>
                ) {
                    Toast.makeText(
                        this@NewUserCreationActivity,
                        getString(R.string.something_went_wrong),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )
    }

    private fun setupViewModel() {
        loaderViewModel = ViewModelProvider(this)[LoaderViewModel::class.java]
        authServiceViewModel = ViewModelProvider(this)[AuthServiceViewModel::class.java]
        userInfoCreationViewModel = ViewModelProvider(this)[UserInfoCreationViewModel::class.java]
    }
}

@Composable
private fun Screen() {

    val actions = LocalActivityBehavior.current

    AppTheme {
        FullScreenLoader {
            Column(
                modifier = Modifier
                    .padding(horizontal = 15.sdp),
            ) {
                BackButton {
                    actions.popActivity()
                }
                UserInfoCreationTitle()
                UserInfoCreationDescription()
                UserNameTextField()
                UserEmailTextField()
                UserNameContinueButton()

                PasswordCreationBottomSheet()
            }
        }
    }
}