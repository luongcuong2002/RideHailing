package com.cuongnl.ridehailing.widgets

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.cuongnl.ridehailing.R

@Composable
fun SimpleAlertDialog(
    onDismissRequest: () -> Unit = {},
    onConfirmation: () -> Unit = {},
    dialogTitle: String,
    dialogText: String,
    showDismissButton: Boolean = true,
    confirmText: String = stringResource(id = R.string.ok_text),
    dismissText: String = stringResource(id = R.string.dismiss_text),
) {
    AlertDialog(
        title = {
            AppText(text = dialogTitle)
        },
        text = {
            AppText(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                AppText(confirmText)
            }
        },
        dismissButton = {
            if (showDismissButton) {
                TextButton(
                    onClick = {
                        onDismissRequest()
                    }
                ) {
                    AppText(dismissText)
                }
            }
        },
    )
}