package design.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import registers.Register

@Composable
fun RegisterRow(values: List<String>) {
    Row(modifier = Modifier.fillMaxWidth()) {
        values.forEach { value ->
            Box(modifier = Modifier.weight(1f)) {
                RegisterRowGrid(
                    value = value
                )
            }
        }
    }
}