package com.mTm.caffeine.ui.screen.welcome.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mTm.caffeine.R
import com.mTm.caffeine.ui.theme.Black


@Composable
fun BoxScope.StarAt(
    alignment: Alignment,
    modifier: Modifier = Modifier,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 0.dp,
) {

    val scale = rememberInfiniteTransition().animateFloat(
        initialValue = 0.0f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Icon(
        painter = painterResource(R.drawable.star),
        contentDescription = null,
        tint = Black.copy(alpha = 0.87f),
        modifier = modifier
            .offset(x = offsetX, y = offsetY)
            .size(16.dp)
            .graphicsLayer(scaleX = scale.value, scaleY = scale.value)
            .align(alignment)
    )
}
