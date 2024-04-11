package design.pages

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
import androidx.compose.ui.zIndex
import design.components.text_fields.CustomTextField
import design.components.text_fields.EncodedModeTextField
import design.components.text_fields.ExplainModeTextField
import screenHeight

@Composable
fun EncodedPage(code: MutableState<String>) {
    Row(modifier = Modifier.fillMaxSize().zIndex(1f)) {
        Box(
            modifier = Modifier.weight(1f).fillMaxHeight(), contentAlignment = Alignment.Center
        ) {
            Box(modifier = Modifier.padding((screenHeight / 80).dp)) {
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
                            tint = Color.Unspecified
                        )
                    },
                    onTextChange = { code.value = it }
                )
            }
        }
        Box(modifier = Modifier.weight(1f)) {
            Box(modifier = Modifier.padding((screenHeight / 80).dp)) {
                EncodedModeTextField(code.value)
            }
        }
    }
}