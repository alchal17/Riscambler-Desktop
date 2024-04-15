package design

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object WindowSize {
    var height by mutableStateOf(100)
    var width by mutableStateOf(100)
}