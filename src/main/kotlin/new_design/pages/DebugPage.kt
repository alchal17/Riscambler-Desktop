package new_design.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import new_design.components.CustomTextField

@Composable
fun DebugPage(code: MutableState<String>) {
    Row(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.weight(1f).fillMaxHeight(),
            contentAlignment = Alignment.BottomEnd
        ) {
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
        Box(modifier = Modifier.weight(1f)) {}
    }
}