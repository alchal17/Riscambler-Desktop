package new_design.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import height
import new_design.pages.Pages
import width

@Composable
fun ButtonsBar(pageState: MutableState<Pages>) {
//    val buttonValues = listOf<Pair<String, () -> Unit>>("Ву", "Practice", "Read More", "Feedback", "???")
    val buttonValues = listOf(
        Pair("Default Mode", Pages.DEFAULT),
        Pair("Explain Mode", Pages.EXPLAIN),
        Pair("Debug Mode", Pages.DEBUG),
        Pair("Reading", Pages.READING),
        Pair("???", Pages.UNKNOWN)
    )
    Box(
        modifier = Modifier.clip(RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp))
            .background(color = Color(39, 58, 70)), contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = (width / 15).dp, vertical = (height / 90).dp),
            horizontalArrangement = Arrangement.spacedBy((width / 15).dp)
        ) {
            buttonValues.forEach {
                OutlinedButton(
                    onClick = { pageState.value = it.second },
                    border = BorderStroke(width = 2.dp, color = Color.Black),
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .border(BorderStroke(width = 2.dp, color = Color.Black), shape = RoundedCornerShape(50))
                        .weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(
                        backgroundColor = if (it.second == pageState.value) Color(90, 75, 75) else Color(169, 82, 82)
                    )
                ) {
                    Text(
                        it.first,
                        style = TextStyle(
                            color = Color.White,
                            fontFamily = FontFamily(
                                Font(
                                    resource = "font/iceberg_regular.ttf",
                                    weight = FontWeight.Thin,
                                    style = FontStyle.Normal
                                )
                            ),
                            fontSize = (width / 70).sp
                        )
                    )
                }
            }
        }
    }
}