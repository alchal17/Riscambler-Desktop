package new_design.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import height
import width

@Composable
fun CustomTextField(code: MutableState<String>, vararg icons: @Composable () -> Unit) {
    Box(modifier = Modifier.background(color = Color.LightGray)) {
        Column(modifier = Modifier.fillMaxSize().padding((height / 150).dp), horizontalAlignment = Alignment.CenterHorizontally) {
            TextField(
                value = code.value,
                onValueChange = { code.value = it },
                modifier = Modifier.fillMaxWidth().weight(10f),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    disabledTextColor = Color.Transparent,
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ), textStyle = TextStyle(fontSize = ((height + width)/100).sp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(0.8f).padding(top = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                icons.forEach { it() }
            }
        }
    }
}