package design.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun LoadingPage() {
    Box(modifier = Modifier.fillMaxSize().background(color = Color(red = 56, green = 71, blue = 80)), contentAlignment = Alignment.Center) {
        Icon(
            painter = painterResource("icons/riscambler-logo-riscer.svg"),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.fillMaxSize(0.75f).padding(10.dp)
        )
    }
}