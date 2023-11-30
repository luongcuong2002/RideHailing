package com.cuongnl.ridehailing.screens.confirmlocation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.screens.confirmlocation.LocalActivityBehavior
import com.cuongnl.ridehailing.widgets.TouchableOpacityButton

@Composable
fun EditIcon() {

    val actions = LocalActivityBehavior.current

    TouchableOpacityButton(
        onClick = {
            actions.editLocation()
        }
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_pencil),
            contentDescription = null,
            modifier = Modifier
                .size(17.dp)
        )
    }
}