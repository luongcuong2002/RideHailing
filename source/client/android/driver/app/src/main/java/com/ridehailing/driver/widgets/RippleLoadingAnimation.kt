package com.ridehailing.driver.widgets

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.delay

@Composable
fun RippleLoadingAnimation(
    modifier: Modifier = Modifier,
    size: Dp = 250.sdp,
    circleColor: Color = Color.Magenta,
    animationDelay: Int = 1500,
    numberOfCircles: Int = 4,
    initialScaleValue: Float = 0.05f
) {

    val circles = mutableListOf<Animatable<Float, AnimationVector1D>>()
        .apply {
            repeat(numberOfCircles) {
                add(remember { Animatable(initialScaleValue) })
            }
        }

    circles.forEachIndexed { index, animatable ->
        LaunchedEffect(Unit) {
            // Use coroutine delay to sync animations
            // divide the animation delay by number of circles
            if (index != 0) {
                delay(timeMillis = (animationDelay / numberOfCircles.toLong()) * index)
            }

            animatable.animateTo(
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = animationDelay,
                        easing = LinearEasing
                    ),
                    repeatMode = RepeatMode.Restart
                )
            )
        }
    }

    // outer circle
    Box(
        modifier = modifier
            .size(size)
            .background(color = Color.Transparent)
    ) {
        // animating circles
        circles.forEachIndexed { index, animatable ->
            Box(
                modifier = Modifier
                    .scale(scale = animatable.value)
                    .size(size)
                    .clip(shape = CircleShape)
                    .background(
                        color = circleColor
                            .copy(alpha = (1 - animatable.value))
                    )
            )
        }
    }
}