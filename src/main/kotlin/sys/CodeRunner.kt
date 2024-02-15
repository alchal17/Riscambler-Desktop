package sys

import androidx.compose.runtime.snapshots.SnapshotStateList
import operations.OpType
import operations.Operation
import registers.Register

class CodeRunner {
    companion object {
        private fun getOperation(codeLine: String): Operation {
            val splitLine = codeLine.split(" ")
            val copiedLine = codeLine

            val command = splitLine[0]
            copiedLine.replace(command, "").replace(" ", "")
            val operands = copiedLine.split(",")

            return Operation(getCmdAsEnum(command), operands)
        }

        private fun getCmdAsEnum(command: String): OpType {
            TODO()
        }

        private fun codeParser(userCode: String): List<Operation> {
            val codeLines = userCode.split("\n")
            val strippedCodeLines = codeLines.map { it.strip() }
            val ops = mutableListOf<Operation>()

            strippedCodeLines.forEach {
                ops.add(getOperation(it))
            }

            return ops
        }

        fun runCode(codeString: String, registers: SnapshotStateList<Register>): Status {
            TODO()
        }
    }
}