package sys

import androidx.compose.runtime.snapshots.SnapshotStateList
import registers.Register

class CodeRunner {
    companion object {
        private fun codeParser() : List<String> {
            TODO()
        }
        fun runCode(codeString: String, registers: SnapshotStateList<Register>) : Status {
            TODO()
        }
    }
}