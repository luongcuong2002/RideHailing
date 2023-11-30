package com.cuongnl.ridehailing.widgets

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.coroutines.delay

@Composable
fun TouchableOpacityButton(
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    onPress: (() -> Unit)? = null,
    onRelease: (() -> Unit)? = null,
    enable: Boolean = true,
    opacity: Float = 0.7f,
    dimmedWhenDisable: Boolean = false,
    opacityWhenDisable: Float = 0.3f,
    content: @Composable BoxScope.() -> Unit
) {
    val updatedEnable = remember { mutableStateOf(enable) }
    val isPressed = remember { mutableStateOf(false) }

    val alpha by animateFloatAsState(
        targetValue = if (updatedEnable.value)
            if (isPressed.value) opacity else 1.0f
        else
            if (dimmedWhenDisable) opacityWhenDisable else 1.0f,
        label = ""
    )

    LaunchedEffect(enable) {
        updatedEnable.value = enable
    }

    Box(
        modifier = Modifier.alpha(alpha)
    ) {
        Box(
            modifier = modifier
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = {
                            if (!updatedEnable.value) {
                                return@detectTapGestures
                            }
                            try {
                                onPress?.let { it() }
                                isPressed.value = true
                                awaitRelease()
                            } finally {
                                onClick?.let { it() }
                                onRelease?.let { it() }
                                delay(200) // duration of animation
                                isPressed.value = false
                            }
                        },
                    )
                }
        ) {
            content()
        }
    }
}