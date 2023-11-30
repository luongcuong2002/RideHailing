package com.cuongnl.ridehailing.screens.notefordriver.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.viewmodel.NoteForDriverUiViewModel
import com.cuongnl.ridehailing.widgets.TextFieldWithBorder
import ir.kaaveh.sdpcompose.sdp

@Composable
fun NoteTextField(
    noteForDriverUiViewModel: NoteForDriverUiViewModel = viewModel()
) {
    Box(
        modifier = Modifier
            .padding(top = 5.sdp)
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 15.sdp, vertical = 20.sdp)
    ) {
        TextFieldWithBorder(
            modifier = Modifier
                .align(Alignment.Center),
            ref = noteForDriverUiViewModel.getNoteTextFieldState(),
            onValueChange = {
                noteForDriverUiViewModel.setNoteTextField(it.text)
            },
            placeholder = stringResource(id = R.string.note_for_driver_hint)
        )
    }
}