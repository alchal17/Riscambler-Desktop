package design.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import height

@Composable
fun CustomTextField(
    code: String,
    lineNumbers: List<Int>,
    scrollState: ScrollState,
    shape: Shape = RoundedCornerShape(10.dp),
    bgColor: Color = Color.LightGray,
    fontSize: Int,
    changeFunction: (String) -> Unit,
    textStyle: TextStyle = TextStyle(
        fontFamily = FontFamily(
            Font(
                resource = "font/notosans_condensed_black.ttf",
                weight = FontWeight.Thin,
                style = FontStyle.Normal
            ),
        ),
        fontSize = fontSize.sp,
        lineHeight = 50.sp
    )
) {
    Box(modifier = Modifier.height((height / 2).dp).fillMaxWidth().clip(shape = shape).background(color = bgColor)) {
        Row {
            Box(
                modifier = Modifier.verticalScroll(scrollState)
            ) {
                Column(modifier = Modifier.padding(horizontal = 4.dp)) {
                    lineNumbers.forEach { lineNumber ->
                        Text(
                            text = "$lineNumber. ", fontSize = fontSize.sp, modifier = Modifier.height(50.dp)
                        )
                    }
                }
            }
            BasicTextField(
                value = code,
                onValueChange = changeFunction,
                modifier = Modifier.fillMaxSize().verticalScroll(scrollState),
                textStyle = textStyle,
            )
        }
    }
}