package com.cuongnl.ridehailing.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class TextEnteredViewModel : ViewModel() {
    private val _text = mutableStateOf("")

    val text: State<String> = _text

    fun setText(text: String) {
        _text.value = text
    }
}