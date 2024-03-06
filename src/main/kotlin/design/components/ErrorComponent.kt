package design.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ErrorComponent(errorText: String, fontSize: Int) {
    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp)).fillMaxWidth().background(color = Color.Red)
    ) {
        Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = null,
            modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp).size(fontSize.dp),
        )
        Text(errorText, modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp), fontSize = fontSize.sp)
    }
}