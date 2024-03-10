package new_design.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import height
import width

@Composable
fun CustomTextField(code: String, textFieldFunc: (String) -> Unit) {
    Box(modifier = Modifier.background(color = Color.LightGray)) {
        Column(modifier = Modifier.fillMaxHeight(0.75f).fillMaxWidth(0.5f).padding((height / 150).dp)) {
            TextField(
                value = code,
                onValueChange = textFieldFunc,
                modifier = Modifier.fillMaxWidth().weight(10f),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    disabledTextColor = Color.Transparent,
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 5.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.clip(
                        CircleShape
                    ).size((width / 50).dp).background(color = Color.Red)
                )
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.clip(
                        CircleShape
                    ).size((width / 50).dp).background(color = Color(23, 188, 109))
                )
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.clip(
                        CircleShape
                    ).size((width / 50).dp).background(color = Color(142, 152, 190))
                )
            }
        }
    }
}