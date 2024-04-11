package design.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import design.components.BottomBar
import registers.Register

@Composable
fun MainPage(registers: List<Register>, windowHeight: Int, windowWidth: Int) {
    val currentPage = remember { mutableStateOf(Pages.DEFAULT) }
    var registerState by remember { mutableStateOf(registers.toMutableStateList()) }
    val code = remember { mutableStateOf("") }
    Scaffold(
        bottomBar = {
            BottomBar(currentPage, windowHeight, windowWidth)
        },
        backgroundColor = Color(red = 56, green = 71, blue = 80),
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.padding(bottom = it.calculateBottomPadding(), top = it.calculateTopPadding())) {
            when (currentPage.value) {
                Pages.DEFAULT -> {
                    DefaultPage(code, registerState, windowHeight, windowWidth)
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
                    EncodedPage(code)
                }

            }
        }
    }
}