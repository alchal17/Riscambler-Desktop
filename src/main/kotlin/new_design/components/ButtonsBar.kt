package new_design.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import width

@Composable
fun ButtonsBar() {
    val buttonValues = listOf("Study", "Practice", "Read More", "Feedback", "???")
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = (width / 15).dp),
        horizontalArrangement = Arrangement.spacedBy((width / 15).dp)
    ) {
        buttonValues.forEach {
            OutlinedButton(
                onClick = {},
                modifier = Modifier.weight(1f),
                border = BorderStroke(width = 2.dp, color = Color.Black),
                colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color(169, 82, 82))
//                169, 82, 82 inactive
//                90, 75, 75 active
            ) {
                Text(
                    it, style = TextStyle(
                        color = Color.White,
                        fontFamily = FontFamily(
                            Font(
                                resource = "font/iceberg_regular.ttf",
                                weight = FontWeight.Thin,
                                style = FontStyle.Normal
                            ),
                        ), fontSize = (width / 70).sp
                    )
                )
            }
        }
    }
}