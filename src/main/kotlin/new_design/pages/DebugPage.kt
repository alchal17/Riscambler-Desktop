package new_design.pages

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun DebugPage(code: String, textFieldFunc: (String) -> Unit){
    Text("Debug Page")
}