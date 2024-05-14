package command_encoder

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object MemoryModel {
    val memoryList = mutableStateListOf<UInt>().apply {
        repeat(100000) {
            add(0u)
        }
    }
}