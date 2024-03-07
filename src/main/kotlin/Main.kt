import androidx.compose.material.MaterialTheme
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import design.pages.CodePage
import registers.Register


fun main() = application {
    Window(
        title = "Riscambler",
        onCloseRequest = ::exitApplication,
        state = rememberWindowState(placement = WindowPlacement.Maximized),
        icon = painterResource("icons/riscambler-logo-riscer.svg")
    ) {
        val registers = listOf(
            Register(regName = "x0", regAltName = "zero"),
            Register(regName = "x1", regAltName = "ra"),
            Register(regName = "x2", regAltName = "sp"),
            Register(regName = "x3", regAltName = "gp"),
            Register(regName = "x4", regAltName = "tp"),
            Register(regName = "x5", regAltName = "t0"),
            Register(regName = "x6", regAltName = "t1"),
            Register(regName = "x7", regAltName = "t2"),
            Register(regName = "x8", regAltName = "s0/fp"),
            Register(regName = "x9", regAltName = "s1"),
            Register(regName = "x10", regAltName = "a0"),
            Register(regName = "x11", regAltName = "a1"),
            Register(regName = "x12", regAltName = "a2"),
            Register(regName = "x13", regAltName = "a3"),
            Register(regName = "x14", regAltName = "a4"),
            Register(regName = "x15", regAltName = "a5"),
            Register(regName = "x16", regAltName = "a6"),
            Register(regName = "x17", regAltName = "a7"),
            Register(regName = "x18", regAltName = "s2"),
            Register(regName = "x19", regAltName = "s3"),
            Register(regName = "x20", regAltName = "s4"),
            Register(regName = "x21", regAltName = "s5"),
            Register(regName = "x22", regAltName = "s6"),
            Register(regName = "x23", regAltName = "s7"),
            Register(regName = "x24", regAltName = "s8"),
            Register(regName = "x25", regAltName = "s9"),
            Register(regName = "x26", regAltName = "s10"),
            Register(regName = "x27", regAltName = "s11"),
            Register(regName = "x28", regAltName = "t3"),
            Register(regName = "x29", regAltName = "t4"),
            Register(regName = "x30", regAltName = "t5"),
            Register(regName = "x31", regAltName = "t6"),
        )
        MaterialTheme {
            CodePage(registers)
        }
    }
}