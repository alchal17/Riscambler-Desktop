package design.components

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import sys.Status

@Composable
fun RunButtonComponent(onClickFun: () -> Unit){
    IconButton(
        onClick = onClickFun,
        modifier = Modifier.clip(shape = RoundedCornerShape(5.dp)).background(color = Color.Green)
    ) {
        Icon(
            imageVector = Icons.Default.PlayArrow,
            contentDescription = "Run a program",
            modifier = Modifier.clip(shape = RoundedCornerShape(5.dp)).background(color = Color.Green)
        )
    }
}