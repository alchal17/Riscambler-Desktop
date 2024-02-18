package design.pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import design.components.RegistersTable
import registers.Register
import sys.CodeRunner
import sys.Status

@Composable
fun CodePage(registers: List<Register>) {
    var registerState by remember { mutableStateOf(registers.toMutableStateList()) }
    var code by remember { mutableStateOf("") }
    var errorText by remember { mutableStateOf("") }
    var errorVisible by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.fillMaxSize(0.9f).padding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                IconButton(
                    onClick = {
                        val status = CodeRunner.runCode(code, registerState)
                        registerState = registers.toMutableStateList()
                        if (status is Status.Error) {
                            errorVisible = true
                            errorText = status.errorMessage
                        } else {
                            errorVisible = false
                        }
                    },
                    modifier = Modifier.clip(shape = RoundedCornerShape(5.dp)).background(color = Color.Green)
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Run a program",
                        modifier = Modifier.clip(shape = RoundedCornerShape(5.dp)).background(color = Color.Green)
                    )
                }
                AnimatedVisibility(visible = errorVisible) {
                    Box(
                        modifier = Modifier.fillMaxWidth(0.5f)
                            .clip(shape = RoundedCornerShape(10.dp)).background(color = Color.Red)
                    ) { Text(errorText, modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)) }
                }

            }

            BasicTextField(
                value = code,
                onValueChange = { code = it },
                modifier = Modifier.fillMaxWidth().fillMaxHeight(0.5f).clip(shape = RoundedCornerShape(10.dp))
                    .background(color = Color.LightGray),
                textStyle = TextStyle(
                    fontFamily = FontFamily(
                        Font(
                            resource = "font/notosans_condensed_black.ttf",
                            weight = FontWeight.Thin,
                            style = FontStyle.Normal
                        )
                    ), fontSize = 80.sp
                )
            )
            RegistersTable(registerState)
        }
    }
}