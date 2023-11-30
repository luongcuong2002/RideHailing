package com.cuongnl.ridehailing.screens.permission

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.activitybehavior.IPermissionActivityBehavior
import com.cuongnl.ridehailing.core.BaseActivity
import com.cuongnl.ridehailing.globalstate.CurrentUser
import com.cuongnl.ridehailing.screens.home.HomeActivity
import com.cuongnl.ridehailing.screens.login.LoginScreen
import com.cuongnl.ridehailing.screens.permission.ui.BannerImage
import com.cuongnl.ridehailing.screens.permission.ui.ContinueButton
import com.cuongnl.ridehailing.theme.AppTheme
import com.cuongnl.ridehailing.utils.PermissionUtils
import com.cuongnl.ridehailing.widgets.AppText
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

val LocalActivityBehavior =
    staticCompositionLocalOf<IPermissionActivityBehavior> { error("No LocalActivityActionsClass provided") }

class PermissionActivity : BaseActivity(), IPermissionActivityBehavior {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (PermissionUtils.isPermissionGranted(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            navigateToNextActivity()
            return
        }

        setContent {
            CompositionLocalProvider(LocalActivityBehavior provides this) {
                Screen()
            }
        }
    }

    override fun requestPermissions() {
        PermissionUtils.requestLocationPermissions(this, PermissionUtils.LOCATION_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PermissionUtils.LOCATION_REQUEST_CODE && grantResults.isNotEmpty()) {
            if (PermissionUtils.isPermissionGranted(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                navigateToNextActivity()
            } else {
                PermissionUtils.requestPreciseLocationPermission(
                    this,
                    PermissionUtils.LOCATION_REQUEST_CODE
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (PermissionUtils.isPermissionGranted(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            navigateToNextActivity()
        }
    }

    private fun navigateToNextActivity() {

        if (CurrentUser.getUser() == null) {
            val intent = Intent(this, LoginScreen::class.java)
            startActivity(intent)
            return
        }

        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}

@Composable
private fun Screen() {
    AppTheme {
        BannerImage()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 13.sdp)
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(12.sdp)
        ) {
            AppText(
                text = stringResource(id = R.string.header_req_per),
                fontWeight = FontWeight.ExtraBold,
                color = colorResource(id = R.color.black),
                fontSize = 17.ssp
            )
            AppText(
                text = stringResource(id = R.string.des_req_per),
                color = colorResource(id = R.color.gray_600),
                fontSize = 10.ssp,
                lineHeight = 14.ssp,
            )
            ContinueButton()
        }
    }
}