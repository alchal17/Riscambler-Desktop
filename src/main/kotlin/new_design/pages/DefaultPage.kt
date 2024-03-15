package new_design.pages

import androidx.compose.animation.Animatable
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import new_design.components.CustomTextField
import new_design.components.ProcessorComponent
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

    val coroutineScope = rememberCoroutineScope()

    Row(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.weight(1f).fillMaxHeight(), contentAlignment = Alignment.Center
        ) {
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
        Box(modifier = Modifier.weight(1f).fillMaxHeight(), contentAlignment = Alignment.Center) {
            ProcessorComponent(registers, backgroundColor)
        }
    }
}