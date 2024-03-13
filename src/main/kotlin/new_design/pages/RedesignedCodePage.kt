package new_design.pages

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import height
import new_design.components.ButtonsBar

@Composable
fun RedesignedCodePage() {
    val currentPage = remember { mutableStateOf(Pages.DEFAULT) }
    val code = remember { mutableStateOf("") }
    val infiniteTransition = rememberInfiniteTransition()

    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(10000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    Box(modifier = Modifier.rotate(rotation)) {
        Scaffold(
            bottomBar = { Box(modifier = Modifier.padding(top = (height / 30).dp)) { ButtonsBar(currentPage) } },
            backgroundColor = Color(red = 56, green = 71, blue = 80),
            modifier = Modifier.fillMaxSize()
        ) {
            Box(modifier = Modifier.padding(bottom = it.calculateBottomPadding(), top = it.calculateTopPadding())) {
                when (currentPage.value) {
                    Pages.DEFAULT -> {
                        DefaultPage(code)
                    }

                    Pages.EXPLAIN -> {
                        ExplainPage(code)
                    }

                    Pages.DEBUG -> {
                        DebugPage(code)
                    }

                    Pages.READING -> {
                        ReadingPage()
                    }

                    Pages.UNKNOWN -> {
                        UnknownPage()
                    }
                }
            }
        }
    }

}