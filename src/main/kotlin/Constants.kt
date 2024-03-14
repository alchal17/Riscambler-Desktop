import constants_enums.ISACommandType
import operations.OperationImplementations
import java.awt.Toolkit

val height = Toolkit.getDefaultToolkit().screenSize.height
val width = Toolkit.getDefaultToolkit().screenSize.width

val commandNamesFormatMap = mapOf(
    "ADD" to ISACommandType.R,
    "SUB" to ISACommandType.R,
    "SLT" to ISACommandType.R,
    "ADDI" to ISACommandType.I,
    "SLTI" to ISACommandType.I
)

val functsRMap = mapOf(
    Pair(0b000000u, 0b000u) to OperationImplementations::ADD,
    Pair(0b010000u, 0b000u) to OperationImplementations::SUB,
    Pair(0b000000u, 0b010u) to OperationImplementations::SLT,
)

val functsIMap = mapOf(
    0b000u to OperationImplementations::ADDI,
)