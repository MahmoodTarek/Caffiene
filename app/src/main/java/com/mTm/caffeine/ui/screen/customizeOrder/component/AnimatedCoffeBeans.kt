package com.mTm.caffeine.ui.screen.customizeOrder.component

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mTm.caffeine.R
import com.mTm.caffeine.ui.screen.customizeOrder.CoffeeStrength


@Composable
fun AnimatedCoffeeBeans(
    isVisible: Boolean,
    strength: CoffeeStrength,
    cupScale: Float
) {
    val transition = updateTransition(targetState = isVisible, label = "BeansTransition")

    val specFloat: FiniteAnimationSpec<Float> =
        spring(dampingRatio = 0.6f, stiffness = Spring.StiffnessVeryLow)
    val specDp: FiniteAnimationSpec<Dp> =
        spring(dampingRatio = 0.6f, stiffness = Spring.StiffnessVeryLow)

    val scale by transition.animateFloat(label = "scale", transitionSpec = { specFloat }) {
        if (it) 0.8f * cupScale else 1.5f * cupScale
    }

    val yOffset by transition.animateDp(label = "yOffset", transitionSpec = { specDp }) {
        if (it) 0.dp else (-300).dp
    }

    val alpha by transition.animateFloat(label = "alpha", transitionSpec = { specFloat }) {
        if (it) strength.targetAlpha else 0f
    }

    Image(
        painter = painterResource(id = R.drawable.coffe_droped),
        contentDescription = "Animated Coffee Beans",
        modifier = Modifier
            .size((180 * cupScale).dp)
            .graphicsLayer {
                this.scaleX = scale
                this.scaleY = scale
                this.translationY = yOffset.toPx()
                this.alpha = alpha
            }
    )
}