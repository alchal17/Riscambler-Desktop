package design.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import registers.Register

@Composable
fun CodePage(registers: SnapshotStateList<Register>) {
    var code by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize().padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        IconButton(
            onClick = {},
            modifier = Modifier.clip(shape = RoundedCornerShape(5.dp)).background(color = Color.Green)
        ) {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "Run a program",
                modifier = Modifier.clip(shape = RoundedCornerShape(5.dp)).background(color = Color.Green)
            )
        }
        TextField(value = code, onValueChange = { code = it }, modifier = Modifier.fillMaxWidth(0.9f))
    }
}