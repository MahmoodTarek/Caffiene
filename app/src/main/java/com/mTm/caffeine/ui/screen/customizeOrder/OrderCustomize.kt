package com.mTm.caffeine.ui.screen.customizeOrder

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mTm.caffeine.R
import com.mTm.caffeine.ui.component.ButtonWithIcon
import com.mTm.caffeine.ui.component.TopBar
import com.mTm.caffeine.ui.component.spacer.VerticalSpacer16
import com.mTm.caffeine.ui.screen.customizeOrder.component.AnimatedCoffeeBeans
import com.mTm.caffeine.ui.screen.customizeOrder.component.CupSizeButtonTemplate
import com.mTm.caffeine.ui.theme.DarkGray
import com.mTm.caffeine.ui.theme.Urbanist
import com.mTm.caffeine.ui.theme.White
import kotlinx.coroutines.delay

@Composable
fun OrderCustomizeScreen(userChoice: String?, navController: NavHostController) {
    var selectedSize by remember { mutableStateOf("M") }
    var coffeePercentage by remember { mutableStateOf("Medium") }
    var previousCoffeePercentage by remember { mutableStateOf("Medium") }
    var animatedStrengths by remember { mutableStateOf(emptySet<String>()) }
    var animationDirection by remember { mutableStateOf("none") }

    val cupSizeScale = when (selectedSize) {
        "S" -> 0.7f
        "M" -> 0.85f
        else -> 1f
    }

    val coffeeDegrees = listOf("Low", "Medium", "High")


    LaunchedEffect(coffeePercentage) {
        val currentIndex = coffeeDegrees.indexOf(coffeePercentage)
        val previousIndex = coffeeDegrees.indexOf(previousCoffeePercentage)

        if (currentIndex > previousIndex) {
            animationDirection = "down"
            animatedStrengths = setOf(coffeePercentage)
            delay(1000)
        } else if (currentIndex < previousIndex) {
            animationDirection = "up"
            animatedStrengths = emptySet()
            delay(1000)
        }

        previousCoffeePercentage = coffeePercentage
    }

    OrderCustomizeContent(
        userChoice = userChoice,
        selectedSize = selectedSize,
        coffeePercentage = coffeePercentage,
        animatedStrengths = animatedStrengths,
        cupScale = cupSizeScale,
        onCoffeeSelected = { newValue ->
            coffeePercentage = newValue
        },
        onSizeSelected = { selectedSize = it },
        onButtonCLick = { navController.navigate("PreparingOrder/${selectedSize}") }
    )
}

@Composable
fun OrderCustomizeContent(
    userChoice: String?,
    selectedSize: String,
    coffeePercentage: String,
    animatedStrengths: Set<String>,
    cupScale: Float,
    onCoffeeSelected: (String) -> Unit,
    onSizeSelected: (String) -> Unit,
    onButtonCLick: () -> Unit
) {
    val logoSize = when (selectedSize) {
        "S" -> 40
        "M" -> 53
        else -> 66
    }
    val cupHeight = when (selectedSize) {
        "S" -> 150
        "M" -> 200
        else -> 400
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .statusBarsPadding()
    ) {
        TopBar(
            caffeType = userChoice,
            icon = painterResource(R.drawable.back_arrow)
        )

        VerticalSpacer16()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(341.dp)
                .padding(vertical = 20.dp),
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

            CoffeeStrength.entries.forEach { strength ->
                AnimatedCoffeeBeans(
                    isVisible = strength.displayName in animatedStrengths,
                    strength = strength,
                    cupScale = cupScale
                )
            }

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

        Row(
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(100.dp))
                .background(White)
                .align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("S", "M", "L").forEach { size ->
                CupSizeButtonTemplate(
                    text = size,
                    isSelected = size == selectedSize,
                    onClick = { onSizeSelected(size) }
                )
            }
        }

        VerticalSpacer16()

        Row(
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(100.dp))
                .background(White)
                .align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("Low", "Medium", "High").forEach { strength ->
                CupSizeButtonTemplate(
                    text = strength,
                    isSelected = strength == coffeePercentage,
                    icon = painterResource(R.drawable.coffe_icon),
                    onClick = { onCoffeeSelected(strength) }
                )
            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.spacedBy(28.dp)
        ) {
            listOf("Low", "Medium", "High").forEach { strength ->
                Text(
                    text = strength,
                    fontFamily = Urbanist,
                    fontWeight = FontWeight.Medium,
                    fontSize = 10.sp,
                    lineHeight = 1.sp,
                    letterSpacing = 0.25.sp,
                    color = DarkGray.copy(alpha = 0.60f)
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
            onClick = { onButtonCLick()}
        )
    }
}


enum class CoffeeStrength(val displayName: String, val targetAlpha: Float) {
    LOW("Low", 0.4f),
    MEDIUM("Medium", 0.6f),
    HIGH("High", 1.0f)
}
