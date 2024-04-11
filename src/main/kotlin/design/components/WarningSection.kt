package design.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun WarningSection(warnings: List<String>) {
    LazyColumn(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
        itemsIndexed(warnings) { index, warning ->
            Text(warning, color = Color.Yellow, modifier = Modifier.padding(horizontal = 5.dp, vertical = 8.dp))
            if (index < warnings.size - 1) {
                Spacer(modifier = Modifier.height(3.dp).fillMaxWidth().background(color = Color.Black))
            }
        }
    }
}