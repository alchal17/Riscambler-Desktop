package sys

import androidx.compose.runtime.snapshots.SnapshotStateList
import operations.LineData
import operations.OpType
import registers.Register

class CodeRunner {
    private val operations = listOf(
        OpType.ArithmeticOps.ADD,
        OpType.ArithmeticOps.ADDI,
        OpType.ArithmeticOps.SUB,
        OpType.ArithmeticOps.SLT,
        OpType.ArithmeticOps.SLTI,
        OpType.ArithmeticOps.SLTU,
        OpType.ArithmeticOps.SLTIU,
        OpType.ArithmeticOps.LUI,
        OpType.ArithmeticOps.AUIP,
        OpType.LoadStoreOps.LD,
        OpType.LoadStoreOps.LW,
        OpType.LoadStoreOps.LH,
        OpType.LoadStoreOps.LB,
        OpType.LoadStoreOps.LWU,
        OpType.LoadStoreOps.LHU,
        OpType.LoadStoreOps.LBU,
        OpType.LoadStoreOps.SD,
        OpType.LoadStoreOps.SW,
        OpType.LoadStoreOps.SH,
        OpType.LoadStoreOps.SB,
        OpType.LogicalOps.AND,
        OpType.LogicalOps.OR,
        OpType.LogicalOps.XOR,
        OpType.LogicalOps.ANDI,
        OpType.LogicalOps.ORI,
        OpType.LogicalOps.XORI,
        OpType.LogicalOps.SLL,
        OpType.LogicalOps.SRL,
        OpType.LogicalOps.SRA,
        OpType.LogicalOps.SLLI,
        OpType.LogicalOps.SRLI,
        OpType.LogicalOps.SRAI,
        OpType.BranchingOps.BEQ,
        OpType.BranchingOps.BNE,
        OpType.BranchingOps.BGE,
        OpType.BranchingOps.BGEU,
        OpType.BranchingOps.BLT,
        OpType.BranchingOps.BLTU,
        OpType.BranchingOps.JAL,
        OpType.BranchingOps.JALR,
        OpType.PseudoInstructionsOps.LI,
        OpType.PseudoInstructionsOps.LA,
        OpType.PseudoInstructionsOps.MV,
        OpType.PseudoInstructionsOps.NOT,
        OpType.PseudoInstructionsOps.NEG,
        OpType.PseudoInstructionsOps.J,
        OpType.PseudoInstructionsOps.CALL,
        OpType.PseudoInstructionsOps.RET,
        OpType.PseudoInstructionsOps.NOP,
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

    private fun runLines(lineDataList: List<LineData>, registers: SnapshotStateList<Register>): EncodingStatus {
        var encodingStatus: EncodingStatus = EncodingStatus.Success
        var operation: OpType
        lineDataList.forEachIndexed { index, lineData ->
            operation = operations.find { it.name == lineData.type.name }!!
            if (operation.commandFunction != null) {
                encodingStatus = operation.commandFunction?.invoke(lineData.operands, registers, index + 1)!!
            }
            if (encodingStatus is EncodingStatus.Error) return encodingStatus
        }
        return encodingStatus
    }

    fun runCode(codeString: String, registers: SnapshotStateList<Register>): EncodingStatus {
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