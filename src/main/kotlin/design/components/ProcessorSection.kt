package design.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import registers.Register
import registers.RegistersViewModel

@Composable
fun ProcessorSection(
    bgColor: Color,
    hoveredRegister: MutableState<Register>,
    isRegisterHovered: MutableState<Boolean>
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        AnimatedVisibility(
            visible = isRegisterHovered.value,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            Box(modifier = Modifier.shadow(elevation = 4.dp)) {
                RegisterInfo(hoveredRegister.value)
            }
        }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Box(modifier = Modifier.shadow(elevation = 4.dp)) {
                ProcessorComponent(
                    RegistersViewModel.registers,
                    bgColor,
                    hoveredRegister,
                    isRegisterHovered,
                )
            }
        }
    }
}