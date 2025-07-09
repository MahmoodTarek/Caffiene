package com.mTm.caffeine.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mTm.caffeine.R
import com.mTm.caffeine.ui.theme.Black

@Composable
fun ButtonWithIcon(
    icon: Painter,
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val contentColor = Color.White.copy(alpha = 0.87f)
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(
                vertical = 18.dp,
                horizontal = 32.dp
            )
            .height(56.dp),
        enabled = true,
        shape = RoundedCornerShape(100),
        colors = ButtonDefaults.buttonColors(containerColor = Black),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
        border = null
    ) {
        Row(
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.25.sp,
                color = contentColor,
                maxLines = 1,
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
            )

            Spacer(Modifier.width(8.dp))

            Icon(
                painter = icon,
                contentDescription = "Button $text",
                tint = contentColor,
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .size(24.dp)
            )
        }
    }
}

@Preview
@Composable
fun ButtonPreview() {
    ButtonWithIcon(
        icon = painterResource(R.drawable.coffe_button),
        text = "bring my coffee",
        onClick = {}
    )
}