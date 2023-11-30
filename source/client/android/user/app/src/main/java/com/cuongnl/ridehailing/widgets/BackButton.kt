package com.cuongnl.ridehailing.widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.activitybehavior.IChangePasswordActivityBehavior
import ir.kaaveh.sdpcompose.sdp

@Composable
fun BackButton(onClick: () -> Unit) {

    NoRippleButton(
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(id = R.drawable.arrow_back),
            contentDescription = null,
            modifier = Modifier
                .padding(bottom = 25.sdp, top = 50.dp)
                .size(25.dp),
        )
    }
}