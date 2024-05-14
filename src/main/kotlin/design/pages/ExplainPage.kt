package design.pages

import MnemonicProvider
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import screenHeight
import design.components.text_fields.CustomTextField
import design.components.text_fields.ExplainModeTextField

@Composable
fun ExplainPage(code: MutableState<String>) {
    val mnemonicProvider = MnemonicProvider()
    var explanation by remember { mutableStateOf("") }
    Row(modifier = Modifier.fillMaxSize().zIndex(1f)) {
        Box(
            modifier = Modifier.weight(1f).fillMaxHeight(), contentAlignment = Alignment.Center
        ) {
            Box(modifier = Modifier.padding((screenHeight / 80).dp)) {
                CustomTextField(code,
                    listOf(
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
                                modifier = Modifier.clickable { explanation = mnemonicProvider.processCode(code.value).joinToString("\n") }
                            )
                        }),
                    onTextChange = { code.value = it }
                )
            }
        }
        Box(modifier = Modifier.weight(1f)) {
            Box(modifier = Modifier.padding((screenHeight / 80).dp)) {
                ExplainModeTextField(explanation)
            }
        }
    }
}