package com.mTm.caffeine.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mTm.caffeine.ui.theme.DarkGray
import com.mTm.caffeine.ui.theme.Urbanist

@Composable
fun TopBar(
    icon: Painter,
    modifier: Modifier = Modifier,
    image: Painter? = null,
    caffeType: String? = null,
) {
    var horizontalArrangement =
        if (caffeType == null) Arrangement.SpaceBetween
        else Arrangement.spacedBy(12.dp)
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = horizontalArrangement
    ) {
        if (caffeType == null && image != null) {
            Image(
                painter = image,
                contentDescription = "Profile Picture",
                alignment = Alignment.CenterStart,
                modifier = Modifier.size(48.dp)
            )

            CircularButton(icon = icon)

        } else if (caffeType != null && image == null) {
            horizontalArrangement = Arrangement.SpaceAround
            CircularButton(icon)
            Text(
                text = caffeType,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 1.sp,
                fontFamily = Urbanist,
                letterSpacing = 0.25.sp,
                color = DarkGray.copy(alpha = 0.87f),
                modifier = Modifier
                    .padding(start = 12.dp)
                    .align(alignment = Alignment.CenterVertically),
            )
        }
    }
}
