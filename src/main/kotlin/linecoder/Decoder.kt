package linecoder

import commandTypeByOpCode
import constants_enums.ISACommandType
import constants_enums.Instruction

class Decoder {
    fun decode(encodedEntry: ULong): Instruction {
        val binaryEntry = (encodedEntry.toString(2).padStart(32, '0')).reversed()
        val opcode = binaryEntry.substring(0, 7)
        val commandType = commandTypeByOpCode[opcode]

        val decodedInstruction: Instruction

        when (commandType) {
            ISACommandType.R -> {
                val func7 = binaryEntry.substring(25, 32)
                val rs2 = binaryEntry.substring(20, 25)
                val rs1 = binaryEntry.substring(15, 20)
                val func3 = binaryEntry.substring(12, 15)
                val rd = binaryEntry.substring(7, 12)

                decodedInstruction = Instruction.RCommand(opcode, rd, func3, rs1, rs2, func7)
            }
            ISACommandType.I -> {
                val immediate = binaryEntry.substring(20, 32)
                val rs1 = binaryEntry.substring(15, 20)
                val func3 = binaryEntry.substring(12, 15)
                val rd = binaryEntry.substring(7, 12)

                decodedInstruction = Instruction.ICommand(immediate, rd, func3, rs1, immediate)
            }
            ISACommandType.S -> {
                val immediate2 = binaryEntry.substring(25, 32)
                val rs2 = binaryEntry.substring(20, 25)
                val rs1 = binaryEntry.substring(15, 20)
                val func3 = binaryEntry.substring(12, 15)
                val immediate1 = binaryEntry.substring(7, 12)

                decodedInstruction = Instruction.SCommand(opcode, immediate1, func3, rs1, rs2, immediate2)
            }
            ISACommandType.SB -> {
                val immediate4 = binaryEntry.substring(31, 32)
                val immediate3 = binaryEntry.substring(25, 31)
                val rs2 = binaryEntry.substring(20, 25)
                val rs1 = binaryEntry.substring(15, 20)
                val func3 = binaryEntry.substring(12, 15)
                val immediate2 = binaryEntry.substring(8, 12)
                val immediate1 = binaryEntry.substring(7, 8)

                decodedInstruction = Instruction.SBCommand(opcode, immediate1, immediate2, func3, rs1, rs2, immediate3, immediate4)
            }
            ISACommandType.U -> {
                val immediate = binaryEntry.substring(12, 32)
                val rd = binaryEntry.substring(7, 12)

                decodedInstruction = Instruction.UCommand(opcode, rd, immediate)
            }
            ISACommandType.UJ -> {
                val immediate4 = binaryEntry.substring(31, 32)
                val immediate3 = binaryEntry.substring(21, 31)
                val immediate2 = binaryEntry.substring(20, 21)
                val immediate1 = binaryEntry.substring(12, 20)
                val rd  = binaryEntry.substring(7, 12)

                decodedInstruction = Instruction.UJCommand(opcode, rd, immediate1, immediate2, immediate3, immediate4)
            }

            null -> throw Exception("Decoder not handled")
        }

        return decodedInstruction
    }
}