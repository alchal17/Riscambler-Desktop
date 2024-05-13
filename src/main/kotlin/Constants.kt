import constants_enums.ISACommandType
import operations.OperationImplementations
import java.awt.Toolkit

val screenHeight = Toolkit.getDefaultToolkit().screenSize.height
val screenWidth = Toolkit.getDefaultToolkit().screenSize.width


val memory: Array<Any?> = arrayOfNulls(Int.MAX_VALUE / 32)

val commandTypeByOpCode = mapOf(
    "0110011" to ISACommandType.R,
    "0010011" to ISACommandType.I,
    "0100011" to ISACommandType.S,
    "1100011" to ISACommandType.SB,
    "0110111" to ISACommandType.U,
    "1101111" to ISACommandType.UJ,
)

val commandNamesFormatMap = mapOf(
    // R type command
    "ADD" to ISACommandType.R,
    "SUB" to ISACommandType.R,
    "SLT" to ISACommandType.R,

    // I type command
    "ADDI" to ISACommandType.I,
    "SLTI" to ISACommandType.I,

    // S type command
    "SB" to ISACommandType.S,

    // SB type command
    "BEQ" to ISACommandType.SB,

    // U type command
    "LUI" to ISACommandType.U,

    // UJ type command
    "UJ" to ISACommandType.UJ,
)

val funct7Map = mapOf(
    "ADD" to "0000000",
    "SUB" to "0100000",

    "SLT" to "0000000",
    "SLTU" to "0000000",

    "AND" to "0000000",
    "OR" to "0000000",
    "XOR" to "0000000",

    "SLL" to "0000000",
    "SRL" to "0000000",
    "SRA" to "0100000",
)

val funct3Map = mapOf(
    "ADD" to "000",
    "SUB" to "000",

    "SLT" to "010",
    "SLTU" to "011",

    "AND" to "111",
    "OR" to "110",
    "XOR" to "100",

    "SLL" to "001",
    "SRL" to "101",
    "SRA" to "101",
)

//val functsIMap = mapOf(
//    0b000u to OperationImplementations::ADDI,
//
//    Pair(null, 0b010u) to OperationImplementations::SLTI,
//    Pair(null, 0b011u) to OperationImplementations::SLTIU,
//
//    Pair(null, 0b111u) to OperationImplementations::ANDI,
//    Pair(null, 0b110u) to OperationImplementations::ORI,
//    Pair(null, 0b100u) to OperationImplementations::XORI,
//
//    Pair(0b0000000u, 0b001u) to OperationImplementations::SLLI,
//    Pair(0b0000000u, 0b101u) to OperationImplementations::SRLI,
//    Pair(0b0100000u, 0b101u) to OperationImplementations::SRAI,
//)

val operationsR = mapOf(
    "00000000000110011" to OperationImplementations::ADD,
    "01000000000110011" to OperationImplementations::SUB,

    "00000000100110011" to OperationImplementations::SLT,
    "00000000110110011" to OperationImplementations::SLTU,

    "00000001110110011" to OperationImplementations::AND,
    "00000001100110011" to OperationImplementations::OR,
    "00000001000110011" to OperationImplementations::XOR,

    "00000000010110011" to OperationImplementations::SLL,
    "00000001010110011" to OperationImplementations::SRL,
    "01000001010110011" to OperationImplementations::SRA,
)