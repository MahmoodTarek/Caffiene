package com.mTm.caffeine.ui.screen.drinks

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.mTm.caffeine.R
import com.mTm.caffeine.ui.component.ButtonWithIcon
import com.mTm.caffeine.ui.component.TopBar
import com.mTm.caffeine.ui.component.spacer.VerticalSpacer24
import com.mTm.caffeine.ui.theme.DarkGray
import com.mTm.caffeine.ui.theme.Gray
import com.mTm.caffeine.ui.theme.LightGray
import com.mTm.caffeine.ui.theme.Urbanist
import kotlin.math.absoluteValue

@Composable
fun DrinkScreen(navController: NavHostController) {
    val cupState = rememberPagerState(
        initialPage = 4,
        initialPageOffsetFraction = 0f,
        pageCount = { 4 }
    )
    val orderChoice = remember { mutableStateOf("Black") }
    DrinksContent(cupState, orderChoice) {
        navController.navigate("OrderCustomize/${orderChoice.value}")
    }

}

@Composable
private fun DrinksContent(
    cupState: PagerState,
    orderChoice: MutableState<String>,
    onButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
            .statusBarsPadding()
    ) {
        TopBar(
            image = painterResource(R.drawable.profile_account),
            icon = painterResource(R.drawable.plus),
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        VerticalSpacer24()

        Text(
            text = "Good Morning",
            fontSize = 36.sp,
            lineHeight = 1.sp,
            fontFamily = Urbanist,
            letterSpacing = 0.25.sp,
            fontWeight = FontWeight.Bold,
            color = LightGray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Text(
            text = "Mahmoud ☀",
            fontSize = 36.sp,
            lineHeight = 1.sp,
            fontFamily = Urbanist,
            letterSpacing = 0.25.sp,
            fontWeight = FontWeight.Bold,
            color = Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Text(
            text = "What would you like to drink today?",
            fontSize = 16.sp,
            lineHeight = 1.sp,
            fontFamily = Urbanist,
            letterSpacing = 0.25.sp,
            fontWeight = FontWeight.Bold,
            color = DarkGray.copy(alpha = 0.80f),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        HorizontalPager(
            state = cupState,
            contentPadding = PaddingValues(horizontal = 92.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 56.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) { page ->

            val imageId = when (page) {
                0 -> R.drawable.unselected_espresso
                1 -> R.drawable.unselected_macchiato
                2 -> R.drawable.unselected_latte
                else -> R.drawable.unselected_black
            }

            val orderName = when (page) {
                0 -> "Espresso"
                1 -> "Macchiato"
                2 -> "Latte"
                else -> "Black"
            }
            if (cupState.currentPage == page) {
                orderChoice.value = orderName
            }

            val pageOffset =
                ((cupState.currentPage - page) + cupState.currentPageOffsetFraction).absoluteValue

            val scale = lerp(0.6f, 1.15f, 1f - pageOffset.coerceIn(0f, 1f))
            val alpha = lerp(0.7f, 1f, 1f - pageOffset.coerceIn(0f, 1f))

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Box(
                    modifier = Modifier
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                            this.alpha = alpha
                        }
                        .zIndex(1f - pageOffset)
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(imageId),
                        contentDescription = "Caffe type",
                        modifier = Modifier.height(244.dp)
                    )
                    Image(
                        painter = painterResource(R.drawable.big_logo),
                        contentDescription = "Brand Logo",
                        modifier = Modifier
                            .padding(top = 35.dp)
                            .height(66.dp)
                            .align(alignment = Alignment.Center)
                    )
                }

                Text(
                    text = orderName,
                    fontSize = 32.sp,
                    fontFamily = Urbanist,
                    letterSpacing = 0.25.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 1.sp,
                    color = DarkGray,
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        ButtonWithIcon(
            icon = painterResource(R.drawable.arrow_right),
            text = "Continue",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp),
            onClick = { onButtonClick() }
        )
    }
}