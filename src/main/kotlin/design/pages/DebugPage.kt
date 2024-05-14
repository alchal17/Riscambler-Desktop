package design.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import design.components.text_fields.CustomTextField
import screenHeight
import design.components.text_fields.DebugModeResultTextField
import registers.Register
import registers.RegistersViewModel
import sys.CodeRunner

@Composable
fun DebugPage(code: MutableState<String>) {
    var nonEmptyRegistersInfo by remember { mutableStateOf("") }
    val codeRunner = CodeRunner()
    var isDebugging by remember { mutableStateOf(false) }
    var currentLine by remember { mutableStateOf(0) }
    Row(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.weight(1f).fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Box(modifier = Modifier.padding((screenHeight / 80).dp)) {
                CustomTextField(
                    code = code,
                    icons = if (!isDebugging) {
                        listOf(
                            {
                                Icon(
                                    painter = painterResource("/icons/clean_field_button.svg"),
                                    contentDescription = null,
                                    tint = Color.Unspecified,
                                    modifier = Modifier.clickable { code.value = "" }
                                        .shadow(elevation = 8.dp, shape = CircleShape)
                                )
                            },
                            {
                                Icon(
                                    painter = painterResource("/icons/debug_button.svg"),
                                    contentDescription = null,
                                    tint = Color.Unspecified,
                                    modifier = Modifier.clickable {
                                        if (code.value.isNotEmpty()) {
                                            isDebugging = true
                                            currentLine = 0
                                            RegistersViewModel.nullifyRegisters()
                                            nonEmptyRegistersInfo =
                                                RegistersViewModel.registers.filter { it.regValue != 0L }
                                                    .joinToString("\n") { "${it.regName}: ${it.regValue}" }
                                        }
                                    }
                                        .shadow(elevation = 8.dp, shape = CircleShape)
                                )
                            })
                    } else listOf(
                        {
                            Icon(
                                painterResource("/icons/stop_icon.svg"),
                                contentDescription = null,
                                tint = Color.Red,
                                modifier = Modifier.fillMaxSize(0.06f).clickable {
                                    isDebugging = false
                                    RegistersViewModel.nullifyRegisters()
                                }.shadow(elevation = 8.dp, shape = CircleShape)
                            )
                        }, {
                            Box(modifier = Modifier.fillMaxHeight(0.06f)) {
                                Text("Current line: ${currentLine + 1}", modifier = Modifier.align(Alignment.Center))
                            }
                        },
                        {
                            Icon(
                                painter = painterResource("/icons/run_button.svg"),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier.clickable {
                                    val lines = code.value.split("\n")
                                    if (lines.isNotEmpty()) {
                                        codeRunner.runCode(lines[currentLine])
                                        nonEmptyRegistersInfo =
                                            RegistersViewModel.registers.filter { it.regValue != 0L }
                                                .joinToString("\n") { "${it.regName}: ${it.regValue}" }
                                    }
                                    println("lines: ${lines.size}. currentLine: $currentLine")
                                    if (lines.size - 1 > currentLine) {
                                        currentLine++
                                    } else {
                                        isDebugging = false
                                        RegistersViewModel.nullifyRegisters()
                                    }
                                }.shadow(elevation = 8.dp, shape = CircleShape)
                            )
                        }),
                    onTextChange = { code.value = it }, readOnly = isDebugging
                )
            }
        }
        Box(modifier = Modifier.weight(1f)) {
            Box(modifier = Modifier.padding((screenHeight / 80).dp).shadow(elevation = 4.dp)) {
                DebugModeResultTextField(nonEmptyRegistersInfo)
            }
        }
    }
}