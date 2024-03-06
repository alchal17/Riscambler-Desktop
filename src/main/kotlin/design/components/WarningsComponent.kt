package design.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import height

@Composable
fun WarningsComponent(warnings: List<String>, fontSize: Int) {
    LazyColumn(modifier = Modifier.fillMaxWidth().height((height/5).dp).background(color = Color.Yellow).clip(shape = RoundedCornerShape(10.dp))) {
        items(warnings) { warning ->
            Text(text = warning, fontSize = fontSize.sp)
        }
    }
}