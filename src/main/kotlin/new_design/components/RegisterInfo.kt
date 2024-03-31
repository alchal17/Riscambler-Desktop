package new_design.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import height
import registers.Register

@Composable
fun RegisterInfo(register: Register) {
    val values = listOf("Name: ${register.regName}", "Alt name: ${register.regAltName}", "Value: ${register.regValue}")
    Box(
        modifier = Modifier.fillMaxHeight(0.1f).clip(
            shape = RoundedCornerShape(0.dp, 0.dp, (height / 100).dp, (height / 100).dp)
        ).background(color = Color.White), contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            values.forEach { value: String ->
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    Text(text = value)
                }
            }
        }
    }
}