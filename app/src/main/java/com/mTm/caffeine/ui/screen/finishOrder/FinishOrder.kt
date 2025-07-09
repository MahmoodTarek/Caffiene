package com.mTm.caffeine.ui.screen.finishOrder

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.mTm.caffeine.R
import com.mTm.caffeine.ui.component.ButtonWithIcon
import com.mTm.caffeine.ui.component.CircularButton
import com.mTm.caffeine.ui.theme.Brown
import com.mTm.caffeine.ui.theme.DarkGray
import com.mTm.caffeine.ui.theme.Gray
import com.mTm.caffeine.ui.theme.Off_white
import com.mTm.caffeine.ui.theme.Urbanist
import com.mTm.caffeine.ui.theme.White

@Composable
fun FinishOrderScreen(navController: NavController) {
    val animationState = remember { MutableTransitionState(false) }

    LaunchedEffect(Unit) {
        animationState.targetState = true
    }

    FinishOrderContent(animationState) { navController.navigate("Sweet") }
}


@Composable
fun FinishOrderContent(
    animationState: MutableTransitionState<Boolean>,
    onButtonCLick: () -> Unit,
) {
    val initialYOffset = (-100).dp
    var isOn by remember { mutableStateOf(false) }
    val transition = updateTransition(animationState, label = "ReadyScreenTransition")

    val animationDuration = 300

    val backgroundColor by animateColorAsState(
        targetValue = if (isOn) Brown else Off_white,
        animationSpec = tween(durationMillis = animationDuration)
    )
    val textColor by animateColorAsState(
        targetValue = if (isOn) White else Gray,
        animationSpec = tween(durationMillis = animationDuration)
    )

    val offsetX by animateDpAsState(
        targetValue = if (isOn) 36.dp else 0.dp,
        animationSpec = tween(durationMillis = animationDuration)
    )

    val topSectionY by transition.animateDp(
        label = "TopOffset",
        transitionSpec = { tween(durationMillis = 800, delayMillis = 200) }
    ) { state ->
        if (state) (-7).dp else initialYOffset
    }

    val contentAlpha by transition.animateFloat(
        label = "ContentAlpha",
        transitionSpec = { tween(durationMillis = 1000, delayMillis = 300) }
    ) { state ->
        if (state) 1f else 0f
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = 68.dp)
                .alpha(contentAlpha),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(contentAlignment = Alignment.Center) {

                Image(
                    painter = painterResource(id = R.drawable.cup),
                    contentDescription = "Cup Body",
                    modifier = Modifier.height(300.dp)
                )

                Image(
                    painter = painterResource(R.drawable.big_logo),
                    contentDescription = "Brand logo",
                    modifier = Modifier
                        .size(66.dp)
                        .align(Alignment.Center)
                )

            }

            Spacer(modifier = Modifier.height(80.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .height(40.dp)
                        .width(76.dp)
                        .clip(RoundedCornerShape(100.dp))
                        .background(backgroundColor)
                        .clickable { isOn = !isOn }
                ) {
                    if (isOn) {

                        Text(
                            text = "OFF",
                            fontSize = 10.sp,
                            fontFamily = Urbanist,
                            fontWeight = FontWeight.Bold,
                            color = textColor,
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(start = 14.dp)
                        )

                    } else {

                        Text(
                            text = "ON",
                            fontSize = 10.sp,
                            fontFamily = Urbanist,
                            fontWeight = FontWeight.Bold,
                            color = textColor,
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(end = 14.dp)
                        )

                    }

                    Icon(
                        painter = painterResource(R.drawable.on_off),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .offset(x = offsetX)
                            .size(40.dp)
                    )

                }
                Text(
                    text = "Take Away",
                    fontSize = 14.sp,
                    fontFamily = Urbanist,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 1.sp,
                    letterSpacing = 0.25.sp,
                    color = DarkGray.copy(alpha = 0.70f),
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                        .padding(start = 8.dp)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .align(Alignment.TopCenter)
                .offset(y = topSectionY),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularButton(
                icon = painterResource(id = R.drawable.close),
                modifier = Modifier
                    .align(alignment = Alignment.Start)
                    .padding(start = 16.dp, top = 16.dp)
            )

            Spacer(modifier = Modifier.height(60.dp))

            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(Color(0xFF7C351B), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.true_icon),
                    contentDescription = "Ready",
                    tint = Color.White
                )

            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Your coffee is ready,\nEnjoy",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Image(
                painter = painterResource(id = R.drawable.cup_cover),
                contentDescription = "Cup Lid",
                modifier = Modifier.width(260.dp)
            )

        }

        ButtonWithIcon(
            icon = painterResource(R.drawable.arrow_right),
            text = "Take snack",
            modifier = Modifier
                .padding(bottom = 24.dp)
                .align(Alignment.BottomCenter),
            onClick = { onButtonCLick() }
        )

    }
}