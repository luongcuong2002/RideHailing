package com.cuongnl.ridehailing.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel

class NoteForDriverUiViewModel : ViewModel() {

    private val _noteTextField = mutableStateOf(TextFieldValue(""))

    val noteTextField: State<TextFieldValue> = _noteTextField

    fun setNoteTextField(text: String) {
        _noteTextField.value = _noteTextField.value.copy(text = text)
    }

    fun getNoteTextFieldState(): MutableState<TextFieldValue> {
        return _noteTextField
    }
}