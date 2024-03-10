package new_design.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import new_design.components.CustomTextField

@Composable
fun DefaultPage(code: String, textFieldFunc: (String) -> Unit) {
    Row(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.weight(1f).fillMaxHeight(),
            contentAlignment = Alignment.BottomEnd
        ) {
            CustomTextField(code, textFieldFunc)
        }
        Box(modifier = Modifier.weight(1f)) {}
    }
}