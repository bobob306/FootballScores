package com.benshapiro.footballscores.ui.components

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

class FSRippleTheme(
    private val color: Color,
): RippleTheme {

    @Composable
    override fun defaultColor(): Color = color

    @Composable
    override fun rippleAlpha(): RippleAlpha =
        RippleAlpha(
            pressedAlpha = 0.15f,
            focusedAlpha = 0.15f,
            draggedAlpha = 0.1f,
            hoveredAlpha = 0.05f,
        )
}