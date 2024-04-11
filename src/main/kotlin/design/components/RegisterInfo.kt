package design.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import screenHeight
import registers.Register

@Composable
fun RegisterInfo(register: Register) {
    val values = listOf(
        "Name: ${register.regName}",
        "Alt name: ${register.regAltName}",
        "Decimal value: ${register.regValue}",
        "Binary value: "
    )
    Column(
        modifier = Modifier.fillMaxHeight(0.08f).clip(
            shape = RoundedCornerShape(0.dp, 0.dp, (screenHeight / 100).dp, (screenHeight / 100).dp)
        ).background(color = Color.White), horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Name: ${register.regName}")
            Text("Alt name: ${register.regAltName}")
        }
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Decimal value: ${register.regValue}")
            Text("Binary value: ${register.regValue.toString(2)}")
            Text("Hex value: ${register.regValue.toString(16)}")
        }
    }
}