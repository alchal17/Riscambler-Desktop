package command_encoder

import commandNamesFormatMap
import constants_enums.ISACommandType
import constants_enums.Instruction
import funct7Map
import funct3Map
import operations.LineData

class Encoder {
    private fun splitCodeLine(codeLine: String): Pair<String, List<String>> {
        val splitLine = codeLine.trim().split(" ")
        var copiedLine = codeLine
        val command = splitLine[0]
        copiedLine = copiedLine.replace(command, "").replace(" ", "")
        val operands = copiedLine.split(",")
        return Pair(command, operands)
    }

    fun encodeLine(codeLine: String, line: Int): EncoderStatus {
        //    ADDI x1, x4, 1000 -> (ADDI, [x1, x4, 1000])
        val splitLine = splitCodeLine(codeLine)

        val commandName = splitLine.first
        val params = splitLine.second

        val commandType = commandNamesFormatMap[commandName]

        val instruction: Instruction

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
                return EncoderStatus.Error("Unknown command: $commandName")
            }
        }
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

    fun getEncodedInstruction(instruction: Instruction): String {
        return when (instruction) {
            is Instruction.ICommand -> "${instruction.immediate}${instruction.rs1}${instruction.func3}${instruction.rd}${instruction.opcode}"
            is Instruction.RCommand -> "${instruction.func7}${instruction.rs2}${instruction.rs1}${instruction.func3}${instruction.rd}${instruction.opcode}"
            is Instruction.SBCommand -> "${instruction.immediate4}${instruction.immediate3}${instruction.rs2}${instruction.rs1}${instruction.func3}${instruction.immediate2}${instruction.immediate1}${instruction.opcode}"
            is Instruction.SCommand -> "${instruction.immediate2}${instruction.rs2}${instruction.rs1}${instruction.func3}${instruction.immediate1}${instruction.opcode}"
            is Instruction.UCommand -> "${instruction.immediate}${instruction.rd}${instruction.opcode}"
            is Instruction.UJCommand -> "${instruction.immediate4}${instruction.immediate3}${instruction.immediate2}${instruction.immediate1}${instruction.rd}${instruction.opcode}"
        }
    }

    fun convertToUint(encodedInstruction: String): ULong {
        var copy = encodedInstruction
        copy = copy.replaceFirst(copy.takeWhile { it == '0' }, "")
        if (copy.isEmpty()) {
            copy = "0"
        }
        println(copy)
        return copy.toULong()
    }
}