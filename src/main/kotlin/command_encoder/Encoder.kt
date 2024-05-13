package command_encoder

import commandNamesFormatMap
import constants_enums.ISACommandType
import constants_enums.Instruction
import funct7Map
import funct3Map

class Encoder {
    private fun split
    fun encodeLine(codeLine: String): EncoderStatus {
        //    ADDI x1, x4, 1000 -> (ADDI, [x1, x4, 1000])
//        val splitLine = (codeLine.trim().replace(",", "")).split(" ")
        val splitLine = codeLine.trim()
        if (splitLine.size < 2) {
            return EncoderStatus.Error("Not enough arguments")
        }

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
                val immediate = params[2].toInt().toString(2).padStart(12, '0')

                instruction = Instruction.ICommand(opcode, rd, func3, rs1, immediate)
            }

            ISACommandType.S -> {
                val opcode = ISACommandType.S.opcode
                val func3 = funct3Map[commandName]
                val rs1 = params[0].substring(1)

                val imm12Rs2 = params[1].substringBefore("(")
                val imm12 = imm12Rs2.substringBeforeLast("(").toInt().toString(2).padStart(12, '0')

                val rs2 = imm12Rs2.substringAfterLast("(").removeSuffix(")")

                val immediate1 = imm12.substring(0, 5)
                val immediate2 = imm12.substring(5, 12)

                instruction = Instruction.SCommand(opcode, immediate1, func3, rs1, rs2, immediate2)
            }

            ISACommandType.SB -> {
                val opcode = ISACommandType.SB.opcode
                val func3 = funct3Map[commandName]
                val rs1 = params[0].substring(1)
                val rs2 = params[1].substring(1)
                val imm12 = params[2].toInt().toString(2).padStart(12, '0')

                val immediate1 = imm12.substring(11, 12)
                val immediate2 = imm12.substring(1, 5)
                val immediate3 = imm12.substring(5, 11)
                val immediate4 = imm12.substring(12, 13)

                instruction =
                    Instruction.SBCommand(opcode, immediate1, immediate2, func3, rs1, rs2, immediate3, immediate4)
            }

            ISACommandType.U -> {
                val opcode = ISACommandType.U.opcode
                val rd = params[0].substring(1)
                val immediate = params[1].toInt().toString(2).padStart(20, '0')

                instruction = Instruction.UCommand(opcode, rd, immediate)
            }

            ISACommandType.UJ -> {
                val opcode = ISACommandType.UJ.opcode
                val rd = params[0].substring(1)
                val immediate = params[1].toInt().toString(2).padStart(20, '0')

                val immediate1 = immediate.substring(12, 20)
                val immediate2 = immediate.substring(11, 12)
                val immediate3 = immediate.substring(1, 11)
                val immediate4 = immediate.substring(20, 21)

                instruction = Instruction.UJCommand(opcode, rd, immediate1, immediate2, immediate3, immediate4)
            }

            else -> {
                println("Type-unknown")
            }
        }

//        return instruction
        return EncoderStatus.Success(instruction)
    }

    fun getEncodedInstruction(instruction: Instruction.RCommand): String {
        return "${instruction.func7}${instruction.rs2}${instruction.rs1}${instruction.func3}${instruction.rd}${instruction.opcode}"
    }

    fun getEncodedInstruction(instruction: Instruction.ICommand): String {
        return "${instruction.immediate}${instruction.rs1}${instruction.func3}${instruction.rd}${instruction.opcode}"
    }

    fun getEncodedInstruction(instruction: Instruction.SCommand): String {
        return "${instruction.immediate2}${instruction.rs2}${instruction.rs1}${instruction.func3}${instruction.immediate1}${instruction.opcode}"
    }

    fun getEncodedInstruction(instruction: Instruction.SBCommand): String {
        return "${instruction.immediate4}${instruction.immediate3}${instruction.rs2}${instruction.rs1}${instruction.func3}${instruction.immediate2}${instruction.immediate1}${instruction.opcode}"
    }

    fun getEncodedInstruction(instruction: Instruction.UCommand): String {
        return "${instruction.immediate}${instruction.rd}${instruction.opcode}"
    }

    fun getEncodedInstruction(instruction: Instruction.UJCommand): String {
        return "${instruction.immediate4}${instruction.immediate3}${instruction.immediate2}${instruction.immediate1}${instruction.rd}${instruction.opcode}"
    }

    fun convertToUint(encodedInstruction: String): UInt {
        return encodedInstruction.toUInt()
    }
}