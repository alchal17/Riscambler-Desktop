package design.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ErrorSection(error: String) {
    Text(error, color = Color.Red, modifier = Modifier.padding(horizontal = 5.dp, vertical = 8.dp))

}