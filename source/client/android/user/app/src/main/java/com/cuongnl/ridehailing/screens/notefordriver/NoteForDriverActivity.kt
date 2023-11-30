package com.cuongnl.ridehailing.screens.notefordriver

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.ViewModelProvider
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.activitybehavior.INoteForDriverActivityBehavior
import com.cuongnl.ridehailing.core.BaseActivity
import com.cuongnl.ridehailing.screens.notefordriver.ui.AppBar
import com.cuongnl.ridehailing.screens.notefordriver.ui.DoneButton
import com.cuongnl.ridehailing.screens.notefordriver.ui.NoteTextField
import com.cuongnl.ridehailing.theme.AppTheme
import com.cuongnl.ridehailing.utils.Constant
import com.cuongnl.ridehailing.viewmodel.NoteForDriverUiViewModel


val LocalActivityBehavior = androidx.compose.runtime.staticCompositionLocalOf<INoteForDriverActivityBehavior> {
    error("No ActivityBehavior provided")
}

class NoteForDriverActivity : BaseActivity(), INoteForDriverActivityBehavior {

    private lateinit var noteForDriverUiViewModel : NoteForDriverUiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModel()

        setContent {
            CompositionLocalProvider(value = LocalActivityBehavior provides this) {
                Screen()
            }
        }
    }

    private fun setupViewModel() {
        noteForDriverUiViewModel = ViewModelProvider(this)[NoteForDriverUiViewModel::class.java]

        val textNote = intent.getStringExtra(Constant.BUNDLE_NOTE_FOR_DRIVER)
        noteForDriverUiViewModel.setNoteTextField(textNote!!)
    }

    override fun clickBackButton() {
        onBackPressed()
    }

    override fun clickDone() {
        val resultIntent = Intent()
        resultIntent.putExtra(Constant.BUNDLE_NOTE_FOR_DRIVER, noteForDriverUiViewModel.noteTextField.value.text.trim())
        setResult(RESULT_OK, resultIntent)

        onBackPressed()
    }

    override fun onBackPressed() {
        finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}

@Composable
private fun Screen() {
    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.app_background_gray))
        ) {
            AppBar()
            NoteTextField()

            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                DoneButton()
            }
        }
    }
}