import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import registers.IntRegister
import registers.Register

@Composable
@Preview
fun App() {
    val registers = listOf<Register>(
        IntRegister(regName = "x0", regAltName = "zero"),
        IntRegister(regName = "x1", regAltName = "ra"),
        IntRegister(regName = "x2", regAltName = "sp"),
        IntRegister(regName = "x3", regAltName = "gp"),
        IntRegister(regName = "x4", regAltName = "tp"),
        IntRegister(regName = "x5", regAltName = "t0"),
        IntRegister(regName = "x6", regAltName = "t1"),
        IntRegister(regName = "x7", regAltName = "t2"),
        IntRegister(regName = "x8", regAltName = "s0/fp"),
        IntRegister(regName = "x9", regAltName = "s1"),
        IntRegister(regName = "x10", regAltName = "a0"),
        IntRegister(regName = "x11", regAltName = "a1"),
        IntRegister(regName = "x12", regAltName = "a2"),
        IntRegister(regName = "x13", regAltName = "a3"),
        IntRegister(regName = "x14", regAltName = "a4"),
        IntRegister(regName = "x15", regAltName = "a5"),
        IntRegister(regName = "x16", regAltName = "a6"),
        IntRegister(regName = "x17", regAltName = "a7"),
        IntRegister(regName = "x18", regAltName = "s2"),
        IntRegister(regName = "x19", regAltName = "s3"),
        IntRegister(regName = "x20", regAltName = "s4"),
        IntRegister(regName = "x21", regAltName = "s5"),
        IntRegister(regName = "x22", regAltName = "s6"),
        IntRegister(regName = "x23", regAltName = "s7"),
        IntRegister(regName = "x24", regAltName = "s8"),
        IntRegister(regName = "x25", regAltName = "s9"),
        IntRegister(regName = "x26", regAltName = "s10"),
        IntRegister(regName = "x27", regAltName = "s11"),
        IntRegister(regName = "x28", regAltName = "t3"),
        IntRegister(regName = "x29", regAltName = "t4"),
        IntRegister(regName = "x30", regAltName = "t5"),
        IntRegister(regName = "x31", regAltName = "t6"),
    )
    val registerState = mutableStateListOf(*registers.toTypedArray())
    MaterialTheme {
        MainWindow(registerState)
    }
}

@Composable
fun MainWindow(registers: SnapshotStateList<Register>) {
    Box(
        modifier = Modifier.fillMaxSize().background(color = Color.Cyan)
            .clickable { registers.add(IntRegister("wewe", "wfwefwef")) }) {
        LazyColumn(modifier = Modifier.fillMaxWidth(), content = { items(registers) { Text(it.regName) } })
    }
}


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
