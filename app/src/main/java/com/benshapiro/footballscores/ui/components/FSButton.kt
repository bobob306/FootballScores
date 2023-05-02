package com.benshapiro.footballscores.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benshapiro.footballscores.R

private val Shape: Shape
    @Composable
    get() = MaterialTheme.shapes.small.copy(all = CornerSize(16.dp))

private val MinHeight = 68.dp
private val MinWidth = 48.dp

@Composable
fun FSButton(
    text: String?,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onClick: () -> Unit,
    icon: Int?,
    enabled: Boolean = true,
    modifier: Modifier,
) {
    val rippleTheme = FSRippleTheme(MaterialTheme.colors.primary)
    val backgroundColor = MaterialTheme.colors.primary
    val disabledBackgroundColor = backgroundColor.copy(alpha = 0.4f)
    val contentColor = MaterialTheme.colors.onPrimary
    val disabledContentColor = contentColor.copy(alpha = 0.4f)
    CompositionLocalProvider(LocalRippleTheme provides rippleTheme) {
        Button(
            onClick = onClick,
            shape = Shape,
            enabled = enabled,
            modifier = modifier
                .heightIn(MinHeight)
                .widthIn(MinWidth),
            interactionSource = interactionSource,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = backgroundColor,
                contentColor = contentColor,
                disabledBackgroundColor = disabledBackgroundColor,
                disabledContentColor = disabledContentColor,
            ),
            content = {
                Row() {
                    val showText = text != null
                    if (icon != null) {
                        Image(
                            painterResource(id = icon),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(end = if (showText) 8.dp else 0.dp)
                                .size(24.dp),
                        )
                    }
                    if (showText) {
                        Text(
                            text = text!!,
                            color = LocalContentColor.current,
                            maxLines = 1,
                        )
                    }
                }
            }
        )
    }
}

@Preview
@Composable
private fun PreviewFSButton() {
    FSButton(
        text = "Premier League",
        icon = R.drawable.premierleagueicon,
        enabled = true,
        interactionSource = remember { MutableInteractionSource() },
        onClick = {},
        modifier = Modifier
    )
}

