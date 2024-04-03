package new_design.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import height

@Composable
fun Analyzer() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth().height((height / 30).dp).background(color = Color(38, 38, 38)),
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(0.1f).fillMaxHeight().background(color = Color(79, 79, 79))
            ) {
                Text("Warnings")
            }
            Box(
                modifier = Modifier.fillMaxWidth(0.1f).fillMaxHeight().background(color = Color(79, 79, 79))
            ) { Text("Errors") }
        }
        Box(modifier = Modifier.fillMaxWidth().height((height * 29 / 30).dp).background(color = Color(59, 255, 219)))
    }
}