package command_encoder

import commandNamesFormatMap
import constants_enums.ISACommandType
import constants_enums.Instruction
import funct7Map
import funct3Map


fun encodeLine(codeLine: String): Instruction {
    val splitLine = (codeLine.trim().replace(",", "")).split(" ")

    val commandName = splitLine[0]
    val params = splitLine.slice(1 until splitLine.size)

    val commandType = commandNamesFormatMap[splitLine[0]]

    lateinit var instruction: Instruction

    when (commandType) {
        ISACommandType.R -> {
            val opcode = ISACommandType.R.opcode
            val rd = params[0].substring(1)
            val func3 = funct3Map[commandName]
            val rs1 = params[1].substring(1)
            val rs2 = params[2].substring(1)
            val func7 = funct7Map[commandName]

            instruction = Instruction.RCommand(opcode, rd, func3, rs1, rs2, func7)
        }
        ISACommandType.I -> {
            val opcode = ISACommandType.I.opcode
            val rd = params[0].substring(1)
            val func3 = funct3Map[commandName]
            val rs1 = params[1].substring(1)
            val immediate = params[2]

            instruction = Instruction.ICommand(opcode, rd, func3, rs1, immediate)
        }
        ISACommandType.S -> {
            val opcode = ISACommandType.S.opcode
            val func3 = funct3Map[commandName]
            val rs1 = params[0].substring(1)

            val imm12Rs2 = params[1].substringBefore("(")
            val imm12 = imm12Rs2.substringBeforeLast("(")

            val rs2 = imm12Rs2.substringAfterLast("(").removeSuffix(")")

            val immediate1 = (imm12.toUInt() and 0x3Fu).toString()
            val immediate2 = ((imm12.toUInt() shr 6) and 0x1Fu).toString()

            instruction = Instruction.SCommand(opcode, immediate1, func3, rs1, rs2, immediate2)
        }
        ISACommandType.SB -> {
            val opcode = ISACommandType.SB.opcode
            val func3 = funct3Map[commandName]
            val rs1 = params[0].substring(1)
            val rs2 = params[1].substring(1)
            val imm12 = params[2]

            val immediate1 = (imm12.toUInt() and 0x1Fu).toString()  // Take the lower 5 bits
            val immediate2 = ((imm12.toUInt() shr 5) and 0x7u).toString()  // Take the next 3 bits
            val immediate3 = ((imm12.toUInt() shr 8) and 0xFu).toString() // Take the next 4 bits
            val immediate4 = ((imm12.toUInt() shr 12) and 0x1u).toString() // Take the highest bit

            instruction = Instruction.SBCommand(opcode, immediate1, immediate2, func3, rs1, rs2, immediate3, immediate4)
        }
        ISACommandType.U -> {
            val opcode = ISACommandType.U.opcode
            val rd = params[0].substring(1)
            val immediate = params[1]

            instruction = Instruction.UCommand(opcode, rd, immediate)
        }
        ISACommandType.UJ -> {
            val opcode = ISACommandType.UJ.opcode
            val rd = params[0].substring(1)
            val immediate = params[1]

            val immediate1 = ((immediate.toUInt() shr 19) and 0x1u).toString() // Take the MSB
            val immediate2 = ((immediate.toUInt() shr 9) and 0xFFu).toString() // Take bits 19:12
            val immediate3 = ((immediate.toUInt() shr 8) and 0x1u).toString() // Take bit 11
            val immediate4 = ((immediate.toUInt() shr 0) and 0x3FFu).toString() // Take bits 10:1

            instruction = Instruction.UJCommand(opcode, rd, immediate1, immediate2, immediate3, immediate4)
        }
        else -> {
            println("Type-unknown")
        }
    }

    return instruction
}