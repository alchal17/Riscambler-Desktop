package design.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextField(
    code: MutableState<String>,
    lineNumbers: List<Int>,
    scrollState: ScrollState,
    shape: Shape = RoundedCornerShape(10.dp),
    bgColor: Color = Color.LightGray,
    fontSize: Int,
    textStyle: TextStyle = TextStyle(
        fontFamily = FontFamily(
            Font(
                resource = "font/notosans_condensed_black.ttf",
                weight = FontWeight.Thin,
                style = FontStyle.Normal
            ),
        ), fontSize = fontSize.sp
    )
) {
    Box(modifier = Modifier.fillMaxHeight(0.5f).fillMaxWidth().clip(shape = shape).background(color = bgColor)) {
        Row {
            Box(
                modifier = Modifier.verticalScroll(scrollState)
            ) {
                Column {
                    lineNumbers.forEach { lineNumber ->
                        Text(
                            text = "$lineNumber. ",
                            modifier = Modifier.padding(horizontal = 4.dp), fontSize = fontSize.sp
                        )
                    }
                }
            }
            BasicTextField(
                value = code.value,
                onValueChange = { newCode ->
                    code.value = newCode
                },
                modifier = Modifier.fillMaxSize().verticalScroll(scrollState),
                textStyle = textStyle,
            )
        }
    }
}