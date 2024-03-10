package new_design.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import height
import new_design.components.ButtonsBar

@Composable
fun RedesignedCodePage() {
    val currentPage = remember { mutableStateOf(Pages.DEFAULT) }
    var code by remember { mutableStateOf("") }
    Scaffold(
        bottomBar = { Box(modifier = Modifier.padding(top = (height / 30).dp)) { ButtonsBar(currentPage) } },
        backgroundColor = Color(red = 56, green = 71, blue = 80),
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.padding(bottom = it.calculateBottomPadding(), top = it.calculateTopPadding())) {
            when (currentPage.value) {
                Pages.DEFAULT -> {
                    DefaultPage(code, { code = it })
                }

                Pages.EXPLAIN -> {
                    ExplainPage(code, { code = it })
                }

                Pages.DEBUG -> {
                    DebugPage(code, { code = it })
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