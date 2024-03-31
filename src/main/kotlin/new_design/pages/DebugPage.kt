package new_design.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import height
import new_design.components.CustomTextField

@Composable
fun DebugPage(code: MutableState<String>) {
    Row(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.weight(1f).fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Box(modifier = Modifier.padding((height / 80).dp)) {
                CustomTextField(
                    code,
                    {
                        Icon(
                            painter = painterResource("/icons/clean_field_button.svg"),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.clickable { code.value = "" })
                    },
                    {
                        Icon(
                            painter = painterResource("/icons/debug_button.svg"),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                    }
                )
            }
        }
        Box(modifier = Modifier.weight(1f)) {}
    }
}