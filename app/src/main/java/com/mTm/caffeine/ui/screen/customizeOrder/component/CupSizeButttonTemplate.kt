package com.mTm.caffeine.ui.screen.customizeOrder.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mTm.caffeine.ui.theme.Brown
import com.mTm.caffeine.ui.theme.DarkGray
import com.mTm.caffeine.ui.theme.LightBrown
import com.mTm.caffeine.ui.theme.White


@Composable
fun CupSizeButtonTemplate(
    text: String,
    isSelected: Boolean,
    icon: Painter? = null,
    onClick: () -> Unit
) {
    var textColor = DarkGray.copy(alpha = 0.60f)
    Box(
        modifier = Modifier
            .size(52.dp)
            .clip(shape = CircleShape)
            .then(
                if (isSelected) {
                    textColor = White
                    Modifier
                        .shadow(
                            elevation = 12.dp,
                            shape = CircleShape,
                            clip = false,
                            spotColor = LightBrown
                        )
                        .background(Brown)
                } else Modifier
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        if (icon == null) {
            Text(
                text = text,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = textColor
            )
        } else if (isSelected) {
            Icon(
                painter = icon,
                contentDescription = "Coffee percentage",
                tint = White
            )

        }
    }
}