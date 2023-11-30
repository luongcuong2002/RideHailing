package com.ridehailing.driver.widgets

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

enum class DragValue { Start, End }

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SwipeableView(
    modifier: Modifier = Modifier,
    background: @Composable () -> Unit,
    thumb: @Composable () -> Unit,
    onSwiped: () -> Unit = {},
    progressTint: Color = Color.Transparent,
) {

    var isSwiped = remember {
        false
    }

    val density = LocalDensity.current
    val animationSpec = tween<Float>(
        durationMillis = 300
    )
    val positionalThreshold = { distance: Float -> distance * 0.7f }
    val velocityThreshold = { with(density) { 125.dp.toPx() } }

    val thumbWidth = remember {
        mutableStateOf(50f)
    }

    val backgroundWidth = remember {
        mutableStateOf(100f)
    }

    val backgroundHeight = remember {
        mutableStateOf(100f)
    }

    val state = rememberSaveable(
        density,
        saver = AnchoredDraggableState.Saver(animationSpec, positionalThreshold, velocityThreshold)
    ) {
        AnchoredDraggableState(
            initialValue = DragValue.Start,
            positionalThreshold = positionalThreshold,
            velocityThreshold = velocityThreshold,
            animationSpec = animationSpec,
            anchors = DraggableAnchors {
                DragValue.Start at thumbWidth.value
                DragValue.End at backgroundWidth.value
            },
            confirmValueChange = {
                if (it == DragValue.End && !isSwiped) {
                    isSwiped = true
                    onSwiped()
                } else {
                    isSwiped = false
                }
                true
            }
        )
    }

    LaunchedEffect(thumbWidth.value + backgroundWidth.value) {
        state.updateAnchors(
            DraggableAnchors {
                DragValue.Start at 0f
                DragValue.End at backgroundWidth.value - thumbWidth.value
            }
        )
    }

    Box(
        modifier = modifier
            .anchoredDraggable(
                state,
                orientation = Orientation.Horizontal
            )
    ) {

        Box(
            modifier = Modifier
                .onGloballyPositioned {
                    backgroundWidth.value = it.size.width.toFloat()
                    backgroundHeight.value = it.size.height.toFloat()
                }
        ) {
            background()
        }

        Box(
            modifier = Modifier
                .height(with(LocalDensity.current) { backgroundHeight.value.toDp() })
                .width(with(LocalDensity.current) { (state.offset + thumbWidth.value).toDp() })
                .clip(CircleShape)
                .background(progressTint)
        )

        Box(
            modifier = Modifier
                .offset(x = with(LocalDensity.current) { state.offset.toDp() })
                .onGloballyPositioned {
                    thumbWidth.value = it.size.width.toFloat()
                }
        ) {
            thumb()
        }
    }
}