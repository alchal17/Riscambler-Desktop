package design.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import registers.Register

@Composable
fun RegistersTable(registers: SnapshotStateList<Register>) {
    LazyColumn(modifier = Modifier.fillMaxWidth(0.9f)) {
        items(registers) { register -> }
    }
}