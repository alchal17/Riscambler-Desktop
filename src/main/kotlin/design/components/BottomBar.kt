package design.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import design.WindowSize
import design.pages.Pages

@Composable
fun BottomBar(pageState: MutableState<Pages>) {
    val buttonValues = Pages.values().toList()
    Box(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(1 / 14f)
            .clip(RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp)).background(color = Color(39, 58, 70))
            .shadow(elevation = 30.dp, shape = RoundedCornerShape(30.dp)),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(0.95f)
        ) {
            buttonValues.forEach { buttonValue ->
                OutlinedButton(
                    onClick = { pageState.value = buttonValue },
                    border = BorderStroke(width = 2.dp, color = Color.Black),
                    modifier = Modifier.width((WindowSize.width / (buttonValues.size + 1)).dp).fillMaxHeight(0.75f)
                        .clip(shape = RoundedCornerShape(50))
                        .border(BorderStroke(width = 2.dp, color = Color.Black), shape = RoundedCornerShape(50))
                        .shadow(elevation = 4.dp, shape = RoundedCornerShape(50), clip = true),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (buttonValue == pageState.value) Color(
                            90,
                            75,
                            75
                        ) else Color(169, 82, 82)
                    )
                ) {
                    Text(
                        buttonValue.pageName,
                        fontSize = ((WindowSize.width + WindowSize.height) / 100).sp,
                        style = TextStyle(
                            color = Color.White,
                            fontFamily = FontFamily(
                                Font(
                                    resource = "font/iceberg_regular.ttf",
                                    weight = FontWeight.Thin,
                                    style = FontStyle.Normal
                                )
                            ),
                        )
                    )
                }
            }
        }
    }
}