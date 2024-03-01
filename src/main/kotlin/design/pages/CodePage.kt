package design.pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import design.components.CustomTextField
import design.components.RegistersTable
import registers.Register
import sys.CodeRunner
import sys.Status

@Composable
fun CodePage(registers: List<Register>) {
    val codeRunner = CodeRunner()
    var registerState by remember { mutableStateOf(registers.toMutableStateList()) }
    val code = remember { mutableStateOf("") }
    var errorText by remember { mutableStateOf("") }
    var errorVisible by remember { mutableStateOf(false) }
    var fonSize by remember { mutableStateOf(40) }
    val lineNumbers = (1..code.value.lines().size).toList()
    val scrollState = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.fillMaxSize(0.9f).padding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text("Font size: $fonSize")
                Column {
                    IconButton(onClick = {
                        if (fonSize < 80) {
                            fonSize += 5
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = {
                        if (fonSize > 10) {
                            fonSize -= 5
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = null
                        )
                    }
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                IconButton(
                    onClick = {
//                        esetting all register values to 0
                        for (i in 0 until registerState.size) {
                            registerState[i].regValue = 0
                        }
                        val status = codeRunner.runCode(codeString = code.value, registers = registerState)
                        registerState = registers.toMutableStateList()
                        if (status is Status.Error) {
                            errorVisible = true
                            errorText = status.errorMessage
                        } else {
                            errorVisible = false
                        }
                        registerState = registerState.toMutableStateList()
                    },
                    modifier = Modifier.clip(shape = RoundedCornerShape(5.dp)).background(color = Color.Green)
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Run a program",
                        modifier = Modifier.clip(shape = RoundedCornerShape(5.dp)).background(color = Color.Green)
                    )
                }
                AnimatedVisibility(visible = errorVisible) {
                    Box(
                        modifier = Modifier.fillMaxWidth(0.5f)
                            .clip(shape = RoundedCornerShape(10.dp)).background(color = Color.Red)
                    ) { Text(errorText, modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)) }
                }

            }
            CustomTextField(
                code = code,
                lineNumbers = lineNumbers,
                scrollState = scrollState,
                fontSize = fonSize
            )
            RegistersTable(registerState)
        }
    }
}