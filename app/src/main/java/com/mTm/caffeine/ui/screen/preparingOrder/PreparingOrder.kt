package com.mTm.caffeine.ui.screen.preparingOrder

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mTm.caffeine.R
import com.mTm.caffeine.ui.screen.preparingOrder.component.WavyLoadingLine
import com.mTm.caffeine.ui.theme.DarkGray
import com.mTm.caffeine.ui.theme.Urbanist
import kotlinx.coroutines.delay

@Composable
fun PreparingOrderScreen(selectedCupSize: String?, navController: NavHostController) {
    PreparingOrderContent(selectedCupSize)
    LaunchedEffect(selectedCupSize) {
        delay(5000)
        navController.navigate("FinishOrder")
    }
}

@Composable
fun PreparingOrderContent(selectedCupSize: String?) {

    val logoSize = when (selectedCupSize) {
        "S" -> 40
        "M" -> 53
        else -> 66
    }
    val cupHeight = when (selectedCupSize) {
        "S" -> 150
        "M" -> 200
        else -> 400
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {

        Spacer(modifier = Modifier.weight(0.2f))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(341.dp)
                .padding(horizontal = 16.dp,vertical = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "$cupHeight ML",
                fontFamily = Urbanist,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                letterSpacing = 0.25.sp,
                color = Color.Black.copy(alpha = 0.60f),
                modifier = Modifier.align(Alignment.TopStart)
            )


            Image(
                painter = painterResource(R.drawable.cup),
                contentDescription = "Cup Size is $cupHeight ML",
                modifier = Modifier
                    .size(cupHeight.dp)
                    .align(Alignment.Center)
            )

            Image(
                painter = painterResource(R.drawable.big_logo),
                contentDescription = "Brand logo",
                modifier = Modifier
                    .size(logoSize.dp)
                    .align(Alignment.Center)
            )
        }



        Spacer(modifier = Modifier.weight(1f))

        WavyLoadingLine()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Almost Done",
                fontFamily = Urbanist,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                lineHeight = 1.sp,
                letterSpacing = 0.25.sp,
                textAlign = TextAlign.Center,
                color = DarkGray.copy(alpha = 0.87f)
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Your coffee will be finish in",
                fontFamily = Urbanist,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                lineHeight = 1.sp,
                letterSpacing = 0.25.sp,
                textAlign = TextAlign.Center,
                color = DarkGray.copy(alpha = 0.60f)
            )

            Row(Modifier.padding(top = 12.dp)) {
                listOf(
                    R.drawable.co,
                    R.drawable.ff,
                    R.drawable.ee
                ).forEach {
                    Image(
                        painter = painterResource(it),
                        contentDescription = "Preparing Time",
                    )
                    if (it != R.drawable.ee) {
                        Image(
                            painter = painterResource(R.drawable.colon),
                            contentDescription = "Colon",
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .align(alignment = Alignment.CenterVertically)
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.weight(0.7f))

    }
}
