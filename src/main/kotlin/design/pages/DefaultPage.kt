package design.pages

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import design.components.HorizontalAnalyzer
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import design.components.text_fields.CustomTextField
import design.components.ProcessorComponent
import design.components.RegisterInfo
import registers.Register
import sys.CodeRunner
import sys.Status

private enum class Element {
    PROCESSOR, ANALYZER
}

@Composable
fun DefaultPage(
    code: MutableState<String>,
    registers: SnapshotStateList<Register>,
    windowHeight: Int,
    windowWidth: Int
) {
    val codeRunner = CodeRunner()
    var animateBackgroundColor by remember { mutableStateOf(false) }

    val backgroundColor by animateColorAsState(
        if (animateBackgroundColor) Color(23, 188, 109) else Color.White,
        animationSpec = tween(1000)
    )
    val hoveredRegister = remember { mutableStateOf(registers[0]) }
    val isRegisterHovered = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    var warnings by remember { mutableStateOf(listOf<String>()) }
    var error by remember { mutableStateOf("") }
    var currentElement by remember { mutableStateOf(Element.PROCESSOR) }

    Row(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.weight(1f).fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().weight(0.0625f)
                    .padding(end = (windowHeight / 80).dp, start = (windowHeight / 80).dp, top = (windowHeight / 80).dp)
                    .background(color = Color(74, 74, 74)),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.weight(1f).fillMaxHeight()
                        .background(color = if (currentElement != Element.PROCESSOR) Color.LightGray else Color.Transparent)
                        .clickable { currentElement = Element.PROCESSOR },
                    contentAlignment = Alignment.Center
                ) {
                    Text("Display mode", color = if (currentElement != Element.PROCESSOR) Color.Black else Color.White)
                }
                Box(
                    modifier = Modifier.weight(1f).fillMaxHeight()
                        .background(color = if (currentElement != Element.ANALYZER) Color.LightGray else Color.Transparent)
                        .clickable { currentElement = Element.ANALYZER },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Analyzer mode",
                        color = if (currentElement != Element.ANALYZER) Color.Black else Color.White
                    )
                }
            }
            Box(modifier = Modifier.padding((windowHeight / 80).dp).weight(1f).shadow(elevation = 4.dp)) {
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
                                for (i in 0 until registers.size) {
                                    registers[i].regValue = 0
                                }
                                val status = codeRunner.runCode(code.value, registers)
                                error = if (status is Status.Error) {
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
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
                this@Row.AnimatedVisibility(
                    visible = isRegisterHovered.value,
                    enter = fadeIn(),
                    exit = fadeOut(),
                ) {
                    Box(modifier = Modifier.shadow(elevation = 4.dp)) {
                        RegisterInfo(hoveredRegister.value)
                    }
                }
            }
            Box(modifier = Modifier.zIndex(3f)) {
                when (currentElement) {
                    Element.PROCESSOR -> Box(modifier = Modifier.zIndex(2f).shadow(elevation = 4.dp)) {
                        ProcessorComponent(
                            registers,
                            backgroundColor,
                            hoveredRegister,
                            isRegisterHovered,
                            windowHeight,
                            windowWidth
                        )
                    }

                    Element.ANALYZER -> HorizontalAnalyzer(warnings, error)
                }

            }

        }
    }
}