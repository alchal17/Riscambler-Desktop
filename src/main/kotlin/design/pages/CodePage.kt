package design.pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import design.components.*
import height
import registers.Register
import sys.CodeRunner
import sys.Status
import width

@Composable
fun CodePage(registers: List<Register>) {
    val codeRunner = CodeRunner()
    var registerState by remember { mutableStateOf(registers.toMutableStateList()) }
    var code by remember { mutableStateOf("") }
    var errorText by remember { mutableStateOf("") }
    var errorVisible by remember { mutableStateOf(false) }
    var warnings by remember { mutableStateOf(listOf<String>()) }
    var fontSize by remember { mutableStateOf(40) }
    val lineNumbers = (1..code.lines().size).toList()
    val scrollState = rememberScrollState()
    val columnScrollState = rememberScrollState()


    Column(
        modifier = Modifier.fillMaxWidth().height(height.dp).verticalScroll(columnScrollState)
            .padding(horizontal = (width / 50).dp, vertical = (height / 200).dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy((height / 40).dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy((width / 50).dp)
            ) {
                RunButtonComponent {
                    //esetting all register values to 0
                    for (i in 0 until registerState.size) {
                        registerState[i].regValue = 0
                    }
                    val status = codeRunner.runCode(codeString = code, registers = registerState)
                    registerState = registers.toMutableStateList()
                    if (status is Status.Error) {
                        errorVisible = true
                        errorText = status.errorMessage
                    } else {
                        errorVisible = false
                    }
                    registerState = registerState.toMutableStateList()
                }
                AnimatedVisibility(visible = warnings.isNotEmpty()) {
                    Box(
                        modifier = Modifier.clip(RoundedCornerShape(5.dp))
                            .background(color = Color.Yellow),
                    ) {
                        Text(
                            text = "Warnings: ${warnings.size}",
                            modifier = Modifier.padding(3.dp),
                            fontSize = (height / 50).sp
                        )
                    }
                }
                AnimatedVisibility(errorVisible) {
                    Box(
                        modifier = Modifier.clip(RoundedCornerShape(5.dp))
                            .background(color = Color.Red),
                    ) {
                        Text(
                            text = errorText,
                            modifier = Modifier.padding(3.dp),
                            fontSize = (height / 50).sp
                        )
                    }
                }
            }
            FontChanger(fun1 = {
                if (fontSize < 80) {
                    fontSize += 5
                }
            }, fun2 = {
                if (fontSize > 10) {
                    fontSize -= 5
                }
            },
                fontSize = fontSize
            )
        }
        CustomTextField(code = code,
            lineNumbers = lineNumbers,
            scrollState = scrollState,
            fontSize = fontSize,
            changeFunction = {
                code = it
                warnings = codeRunner.checkWarnings(it)
            })
        RegistersTable(registerState)
        Column {
            AnimatedVisibility(visible = errorVisible) {
                ErrorComponent(errorText, fontSize = fontSize)
            }
            AnimatedVisibility(visible = warnings.isNotEmpty()) {
                WarningsComponent(warnings, fontSize = fontSize)
            }

        }
    }
}