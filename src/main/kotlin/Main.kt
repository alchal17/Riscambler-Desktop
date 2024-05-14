import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import design.Screens
import design.WindowSize
import design.pages.LoadingPage
import design.pages.MainPage
import kotlinx.coroutines.delay


fun main() = application {
    Window(
        title = "Riscambler",
        onCloseRequest = ::exitApplication,
        state = rememberWindowState(placement = WindowPlacement.Maximized),
        icon = painterResource("icons/riscambler-logo-riscer.svg"),
        resizable = false,
    ) {
        var initialAnimationState by remember { mutableStateOf(false) }
        var currentPage by remember { mutableStateOf(Screens.LOADING_SCREEN) }
        MaterialTheme {
            Box(
                modifier = Modifier.fillMaxSize().background(color = Color(red = 56, green = 71, blue = 80))
                    .onSizeChanged { size ->
//                        windowHeight = size.height
//                        windowWidth = size.width
                        WindowSize.height = size.height
                        WindowSize.width = size.width
                    }) {
                AnimatedVisibility(
                    initialAnimationState,
                    enter = scaleIn(animationSpec = tween(1000)),
                    exit = scaleOut(animationSpec = tween(1500))
                ) {
                    LoadingPage()
                }
                AnimatedVisibility(
                    currentPage == Screens.CODE_SCREEN, enter = fadeIn(animationSpec = tween(1500)), exit = fadeOut()
                ) { MainPage() }

            }
        }
        LaunchedEffect(key1 = true) {
            initialAnimationState = true
            delay(2500)
            initialAnimationState = false
            currentPage = Screens.CODE_SCREEN
        }
    }

}