package com.mTm.caffeine.ui.screen.welcome

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mTm.caffeine.R
import com.mTm.caffeine.ui.component.ButtonWithIcon
import com.mTm.caffeine.ui.component.TopBar
import com.mTm.caffeine.ui.component.spacer.VerticalSpacer24
import com.mTm.caffeine.ui.component.spacer.VerticalSpacer33
import com.mTm.caffeine.ui.screen.welcome.component.StarAt
import com.mTm.caffeine.ui.theme.Black
import com.mTm.caffeine.ui.theme.Sniglet

@Composable
fun WelcomeScreen(navController: NavHostController) {
    val transition = rememberInfiniteTransition()

    val offsetY = transition.animateValue(
        initialValue = 0.dp,
        targetValue = 20.dp,
        typeConverter = Dp.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val shadowAlpha = transition.animateFloat(
        initialValue = 0f,
        targetValue = 0.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    WelcomeContent(
        offsetY = offsetY.value,
        shadowAlpha = shadowAlpha.value
    ) {
        navController.navigate("Drink")
    }
}
@Composable
private fun WelcomeContent(
    offsetY: Dp,
    shadowAlpha: Float,
    onButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .statusBarsPadding()
    ) {
        TopBar(
            image = painterResource(R.drawable.profile_account),
            icon = painterResource(R.drawable.plus)
        )

        VerticalSpacer24()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 81.dp, end = 91.dp)
        ) {
            Text(
                text = "Focus\nFocus\nI Need Coffee\nto Focus",
                fontSize = 32.sp,
                lineHeight = 50.sp,
                fontFamily = Sniglet,
                fontWeight = FontWeight.Normal,
                color = Black.copy(alpha = 0.87f),
                textAlign = TextAlign.Center
            )

            StarAt(alignment = Alignment.TopEnd, offsetY = 6.dp)
            StarAt(alignment = Alignment.TopStart, offsetY = 65.dp)
            StarAt(alignment = Alignment.BottomEnd, offsetX = 12.dp)
        }

        VerticalSpacer33()

        Image(
            painter = painterResource(R.drawable.ghost),
            contentDescription = "Ghost Animation",
            modifier = Modifier
                .size(244.dp)
                .offset(y = offsetY)
                .align(Alignment.CenterHorizontally)
        )

        Box(
            modifier = Modifier
                .padding(top = 28.dp)
                .size(width = 160.dp, height = 24.dp)
                .offset(y = -offsetY)
                .clip(RoundedCornerShape(100))
                .graphicsLayer { alpha = shadowAlpha }
                .background(Black.copy(alpha = 0.14f))
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.weight(1f))

        ButtonWithIcon(
            icon = painterResource(R.drawable.coffe_button),
            text = "bring my coffee",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp),
            onClick = onButtonClick
        )
    }
}


