package new_design.pages

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import height
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import new_design.components.Analyzer
import new_design.components.text_fields.CustomTextField
import new_design.components.ProcessorComponent
import new_design.components.RegisterInfo
import registers.Register
import sys.CodeRunner

@Composable
fun DefaultPage(code: MutableState<String>, registers: SnapshotStateList<Register>) {
    val codeRunner = CodeRunner()
    var animateBackgroundColor by remember { mutableStateOf(false) }

    val backgroundColor by animateColorAsState(
        if (animateBackgroundColor) Color(23, 188, 109) else Color.White,
        animationSpec = tween(1000)
    )
    val hoveredRegister = remember { mutableStateOf(registers[0]) }
    val isRegisterHovered = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Row(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.weight(1f).fillMaxHeight(), contentAlignment = Alignment.Center
        ) {
            Box(modifier = Modifier.padding((height / 80).dp)) {
                CustomTextField(code,
                    {
                        Icon(painter = painterResource("/icons/clean_field_button.svg"),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.clickable { code.value = "" })
                    }, {
                        Icon(
                            painter = painterResource("/icons/run_button.svg"),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.clickable {
                                for (i in 0 until registers.size) {
                                    registers[i].regValue = 0
                                }
                                codeRunner.runCode(code.value, registers)
                                animateBackgroundColor = !animateBackgroundColor
                                coroutineScope.launch {
                                    delay(1000) // Delay before resetting the animation state
                                    animateBackgroundColor = false
                                }
                            }
                        )
                    }
                )
            }
        }
        Box(modifier = Modifier.weight(1f).fillMaxHeight(), contentAlignment = Alignment.Center) {
            Box(modifier = Modifier.zIndex(2f)) {
                ProcessorComponent(registers, backgroundColor, hoveredRegister, isRegisterHovered)
            }
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
                this@Row.AnimatedVisibility(
                    visible = isRegisterHovered.value,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    RegisterInfo(hoveredRegister.value)
                }
            }
        }
    }
}