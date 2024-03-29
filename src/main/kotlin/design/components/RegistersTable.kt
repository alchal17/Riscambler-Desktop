package design.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import height
import registers.Register

@Composable
fun RegistersTable(registers: SnapshotStateList<Register>) {
    Column(modifier = Modifier.clip(shape = RoundedCornerShape(10.dp)).background(color = Color.Black)) {
        RegisterRow(listOf("Name", "Alt name", "Integer", "Binary", "Hex"))
        LazyColumn(modifier = Modifier.fillMaxWidth().height((height / 3).dp)) {
            items(registers) { register ->
                RegisterRow(
                    listOf(
                        register.regName,
                        register.regAltName,
                        register.regValue.toString(),
                        register.regValue.toString(2),
                        register.regValue.toString(16),
                    )
                )
            }
        }
    }
}