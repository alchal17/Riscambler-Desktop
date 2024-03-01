package sys

import androidx.compose.runtime.snapshots.SnapshotStateList
import operations.LineData
import operations.OpType
import registers.Register

class CodeRunner {
    private val operations = listOf(
        OpType.ArithmeticOps.ADD,
        OpType.ArithmeticOps.ADDI,
        OpType.ArithmeticOps.FADD,
        OpType.ArithmeticOps.SUB,
        OpType.ArithmeticOps.FSUB,
        OpType.ArithmeticOps.MUL,
        OpType.ArithmeticOps.DIV,
        OpType.ArithmeticOps.REM,
        OpType.ArithmeticOps.NEG,
        OpType.ControlTransferOps.JAL,
        OpType.ControlTransferOps.JALR,
        OpType.ControlTransferOps.BEQ,
        OpType.ControlTransferOps.BNE,
        OpType.ControlTransferOps.BLT,
        OpType.ControlTransferOps.BGE,
        OpType.ComparisonOps.SLT,
        OpType.ComparisonOps.SLTU,
        OpType.ComparisonOps.SLTI,
        OpType.ComparisonOps.SLTIU,
        OpType.DataTransferOps.LW,
        OpType.DataTransferOps.SW,
        OpType.DataTransferOps.LB,
        OpType.DataTransferOps.SB,
        OpType.LogicalOps.AND,
        OpType.LogicalOps.OR,
        OpType.LogicalOps.XOR,
        OpType.LogicalOps.NOT,
        OpType.ShiftAndBitManipulationOps.SLL,
        OpType.ShiftAndBitManipulationOps.SRL,
        OpType.ShiftAndBitManipulationOps.SRA,
        OpType.ShiftAndBitManipulationOps.ROL,
        OpType.ShiftAndBitManipulationOps.ROR,
    )

    private fun getOperation(codeLine: String): LineData? {
        val splitLine = codeLine.split(" ")
        var copiedLine = codeLine
        val command = splitLine[0]
        copiedLine = copiedLine.replace(command, "").replace(" ", "")
        val operands = copiedLine.split(",")
        val enum = getCmdAsEnum(command = command)
        return if (enum == null) {
            null
        } else {
            LineData(type = enum, operands = operands)
        }
    }

    private fun getCmdAsEnum(command: String): OpType? {
        return operations.find { it.name == command.uppercase() }
    }

    private fun getLinesAsData(userCode: String): List<LineData> {
        val codeLines = userCode.split("\n")
        val strippedCodeLines = codeLines.map { it.strip() }
        val ops = mutableListOf<LineData>()
        var operation: LineData?
        strippedCodeLines.forEach {
            operation = getOperation(codeLine = it)
            if (operation != null) {
                ops.add(operation!!)
            }
        }

        return ops
    }

    private fun runLines(lineDataList: List<LineData>, registers: SnapshotStateList<Register>): Status {
        var status: Status = Status.Success
        var operation: OpType
        lineDataList.forEachIndexed { index, lineData ->
            operation = operations.find { it.name == lineData.type.name }!!
            if (operation.commandFunction != null) {
                status = operation.commandFunction?.invoke(lineData.operands, registers, index + 1)!!
            }
            if (status is Status.Error) return status
        }
        return status
    }

    fun runCode(codeString: String, registers: SnapshotStateList<Register>): Status {
        val strippedLinesData = getLinesAsData(codeString)
        return runLines(strippedLinesData, registers)
    }


    private fun getSimilarCommands(command: String): List<String> {
        val commands =
            operations.filter { it.name.startsWith(command.uppercase()) || it.name.endsWith(command.uppercase()) }
        return commands.map { it.name }
    }

    fun checkWarnings(codeString: String): List<String> {
        val result = mutableListOf<String>()
        var currentCommand: String
        var possibleCommands: List<String>
        var lineNumber = 1
        for (line in codeString.split("\n")) {
            currentCommand = line.strip().split(" ")[0]
            if (currentCommand != "" && getCmdAsEnum(currentCommand) == null) {
                possibleCommands = getSimilarCommands(currentCommand)
                result.add(
                    if (possibleCommands.isEmpty()) "Line $lineNumber: Unknown command: '$currentCommand'." else "Line $lineNumber: Unknown command: '$currentCommand'. Did you mean any of these: ${
                        possibleCommands.joinToString(
                            separator = ", "
                        )
                    }?"
                )
            }
            lineNumber++
        }
        return result
    }
}