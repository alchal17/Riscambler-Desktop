package design.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

data class SwitchPanelDataClass(val textColor: Color, val name: String, val onClick: () -> Unit, val bgColor: Color)

@Composable
fun SwitchDisplayAnalyzerPanel(panelValues: List<SwitchPanelDataClass>) {
    Row(
        modifier = Modifier.fillMaxSize().background(color = Color(74, 74, 74)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        panelValues.forEach { panelValue ->
            Box(
                modifier = Modifier.weight(1f).fillMaxHeight()
                    .background(color = panelValue.bgColor)
                    .clickable { panelValue.onClick() },
                contentAlignment = Alignment.Center
            ) {
                Text(panelValue.name, color = panelValue.textColor)
            }
        }
    }
}