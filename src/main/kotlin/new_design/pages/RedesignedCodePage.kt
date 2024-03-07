package new_design.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import new_design.components.ButtonsBar

@Composable
fun RedesignedCodePage() {
    Scaffold(
        bottomBar = { Box(modifier = Modifier.padding(vertical = 15.dp)) { ButtonsBar() } },
        backgroundColor = Color(red = 56, green = 71, blue = 80),
        modifier = Modifier.fillMaxSize()
    ) { }

}