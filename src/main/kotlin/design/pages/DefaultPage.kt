package design.pages

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import design.WindowSize
import design.components.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import design.components.text_fields.CustomTextField
import registers.RegistersViewModel
import sys.CodeRunner
import sys.Memory
import sys.RunStatus

private enum class Element {
    PROCESSOR, ANALYZER
}

@Composable
fun DefaultPage(
    code: MutableState<String>,
) {
    val codeRunner = CodeRunner()
    var animateBackgroundColor by remember { mutableStateOf(false) }

    val backgroundColor by animateColorAsState(
        if (animateBackgroundColor) Color(23, 188, 109) else Color.White,
        animationSpec = tween(1000)
    )
    val hoveredRegister = remember { mutableStateOf(RegistersViewModel.registers[0]) }
    val isRegisterHovered = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    var warnings by remember { mutableStateOf(listOf<String>()) }
    var error by remember { mutableStateOf("") }
    var currentElement by remember { mutableStateOf(Element.PROCESSOR) }

    Row(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.weight(1f).fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().weight(0.0625f).padding(
                    end = (WindowSize.height / 80).dp,
                    start = (WindowSize.height / 80).dp,
                    top = (WindowSize.height / 80).dp
                )
            ) {
                SwitchDisplayAnalyzerPanel(
                    listOf(
                        SwitchPanelDataClass(
                            name = "Display mode",
                            textColor = if (currentElement != Element.PROCESSOR) Color.Black else Color.White,
                            onClick = { currentElement = Element.PROCESSOR },
                            bgColor = if (currentElement != Element.PROCESSOR) Color.LightGray else Color.Transparent
                        ), SwitchPanelDataClass(
                            name = "Analyzer mode",
                            textColor = if (currentElement != Element.ANALYZER) Color.Black else Color.White,
                            onClick = { currentElement = Element.ANALYZER },
                            bgColor = if (currentElement != Element.ANALYZER) Color.LightGray else Color.Transparent
                        )
                    )
                )
            }
            Box(modifier = Modifier.padding((WindowSize.height / 80).dp).weight(1f).shadow(elevation = 4.dp)) {
                CustomTextField(code,
                    {
                        Icon(painter = painterResource("/icons/clean_field_button.svg"),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.clickable { code.value = "" }
                                .shadow(elevation = 8.dp, shape = CircleShape)
                        )
                    }, {
                        Icon(
                            painter = painterResource("/icons/run_button.svg"),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.clickable {
                                RegistersViewModel.nullifyRegisters()
                                Memory.clean()
                                val status = codeRunner.runCode(code.value)
                                error = if (status is RunStatus.Error) {
                                    status.errorMessage
                                } else {
                                    ""
                                }
                                animateBackgroundColor = !animateBackgroundColor
                                coroutineScope.launch {
                                    delay(1000)
                                    animateBackgroundColor = false
                                }
                            }.shadow(elevation = 8.dp, shape = CircleShape)
                        )
                    },
                    onTextChange = { textFieldValue: String ->
                        code.value = textFieldValue
                        warnings = codeRunner.checkWarnings(textFieldValue)
                    }
                )
            }
        }
        Box(modifier = Modifier.weight(1f).fillMaxHeight(), contentAlignment = Alignment.Center) {
            when (currentElement) {
                Element.PROCESSOR -> ProcessorSection(
                    bgColor = backgroundColor,
                    hoveredRegister = hoveredRegister,
                    isRegisterHovered = isRegisterHovered
                )

                Element.ANALYZER -> HorizontalAnalyzer(warnings = warnings, error = error)
            }

        }
    }
}