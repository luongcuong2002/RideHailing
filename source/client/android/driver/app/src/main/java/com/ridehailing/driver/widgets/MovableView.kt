package com.ridehailing.driver.widgets

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.MarqueeAnimationMode.Companion.Immediately
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovableView(
    modifier: Modifier = Modifier,
    iterations: Int = Int.MAX_VALUE,
    animationMode: MarqueeAnimationMode = Immediately,
    delayMillis: Int = 0,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = modifier.basicMarquee(
            iterations = iterations,
            animationMode = animationMode,
            delayMillis = delayMillis
        )
    ) {
        content()
    }
}