package linecoder

import commandNamesFormatMap
import constants_enums.ISACommandType
import funct3Map
import funct7Map
import opCodeByCommandType
import java.lang.Exception


sealed class Encoder
{
    class OperandEncoder {
        companion object {
            fun encodeReg(reg: String) : String {
                return reg.substring(1).toInt().toString(2).padStart(5, '0')
            }

            fun encodeImmediateForI(immediate: String) : String {
                return immediate.toInt().toString(2).reversed().padStart(12, '0')
            }
            fun encodeImmediateForS(immediate: String): String {
                return immediate.toInt().toString(2).reversed().padStart(12, '0')
            }
            fun encodeImmediateForSB(immediate: String): String {
                return immediate.toInt().toString(2).reversed().padStart(12, '0')
            }
            fun encodeImmediateForU(immediate: String) : String {
                return immediate.toInt().toString(2).reversed().padStart(20, '0')
            }
            fun encodeImmediateForUJ(immediate: String) : String {
                return immediate.toInt().toString(2).reversed().padStart(20, '0')
            }
        }
    }

    class CommandEncoder {
        companion object {
            fun encode_R_CommandOperands(params: List<String>): ArrayList<String> {
                val encodedOperands = arrayListOf<String>()
                var encoded: String

                for (param in params) {
                    if (param.startsWith("x")) {
                        encoded = OperandEncoder.encodeReg(param)
                    } else {
                        throw Exception("Wrong operand type at $param")
                    }
                    encodedOperands.add(encoded)
                }

                return encodedOperands
            }

            fun encode_I_CommandOperands(params: List<String>) : ArrayList<String> {
                val encodedOperands = arrayListOf<String>()
                var encoded: String

                for (param in params) {
                    if (param.startsWith("x")) {
                        encoded = OperandEncoder.encodeReg(param)
                    } else if (param.isAllDigits()) {
                        encoded = OperandEncoder.encodeImmediateForI(param)
                    } else {
                        throw Exception("Wrong operand type at $param")
                    }
                    encodedOperands.add(encoded)
                }

                return encodedOperands
            }

            fun encode_S_CommandOperands(params: List<String>) : ArrayList<String> {
                val encodedOperands = arrayListOf<String>()
                var encoded: String = ""

                for (param in params) {
                    if (param.startsWith("x")) {
                        encoded = OperandEncoder.encodeReg(param)
                        encodedOperands.add(encoded)
                    } else if (param.contains("(") or param.contains(")")) {
                        val opers = param.split("(")

                        val imm = opers[0]
                        val rs1 = opers[1].replace(")", "")
                        encodedOperands.add(rs1)
                        encodedOperands.add(imm)
                    } else {
                        throw Exception("Wrong operand type at $param")
                    }
                }

                return encodedOperands
            }

            fun encode_SB_CommandOperands(params: List<String>) : ArrayList<String> {
                val encodedOperands = arrayListOf<String>()
                var encoded: String = ""

                for (param in params) {
                    if (param.startsWith("x")) {
                        encoded = OperandEncoder.encodeReg(param)
                    } else if (param.isAllDigits()) {
                        encoded = OperandEncoder.encodeImmediateForSB(param)
                    } else {
                        throw Exception("Wrong operand type at $param")
                    }
                    encodedOperands.add(encoded)
                }

                return encodedOperands
            }

            fun encode_U_CommandOperands(params: List<String>) : ArrayList<String> {
                val encodedOperands = arrayListOf<String>()
                var encoded: String = ""

                for (param in params) {
                    if (param.startsWith("x")) {
                        encoded = OperandEncoder.encodeReg(param)
                    } else if (param.isAllDigits()) {
                        encoded = OperandEncoder.encodeImmediateForU(param)
                    } else {
                        throw Exception("Wrong operand type at $param")
                    }
                    encodedOperands.add(encoded)
                }

                return encodedOperands
            }

            fun encode_UJ_CommandOperands(params: List<String>) : ArrayList<String> {
                val encodedOperands = arrayListOf<String>()
                var encoded: String = ""

                for (param in params) {
                    if (param.startsWith("x")) {
                        encoded = OperandEncoder.encodeReg(param)
                    } else if (param.isAllDigits()) {
                        encoded = OperandEncoder.encodeImmediateForUJ(param)
                    } else {
                        throw Exception("Wrong operand type at $param")
                    }
                    encodedOperands.add(encoded)
                }

                return encodedOperands
            }
        }
    }

    class InstructionEncoder {
        companion object {
            fun encodeInstructionR(commandName: String, encodedOperands: ArrayList<String>) : String {
                val opcode = getOpcodeByCommandName(commandName)
                val func7 = funct7Map[commandName] ?: throw Exception("Wrong command $commandName")
                val func3 = funct3Map[commandName] ?: throw Exception("Wrong command $commandName")

                return "${func7}${encodedOperands[2]}${encodedOperands[1]}${func3}${encodedOperands[0]}${opcode}"
            }

            fun encodeInstructionI(commandName: String, encodedOperands: ArrayList<String>) : String {
                val opcode = getOpcodeByCommandName(commandName)
                val func3 = funct3Map[commandName] ?: throw Exception("Wrong command $commandName")

                return "${encodedOperands[2]}${encodedOperands[1]}${func3}${encodedOperands[0]}${opcode}"
            }

            fun encodeInstructionS(commandName: String, encodedOperands: ArrayList<String>) : String {
                val opcode = getOpcodeByCommandName(commandName)
                val func3 = funct3Map[commandName] ?: throw Exception("Wrong command $commandName")

                val imm = encodedOperands[2]
                val imm1 = imm.substring(0, 5)
                val imm2 = imm.substring(5, 12)

                return "${imm2}${encodedOperands[0]}${encodedOperands[1]}${func3}${imm1}${opcode}"
            }

            fun encodeInstructionSB(commandName: String, encodedOperands: ArrayList<String>) : String {
                val opcode = getOpcodeByCommandName(commandName)
                val func3 = funct3Map[commandName] ?: throw Exception("Wrong command $commandName")

                val imm = encodedOperands[2]
                val imm1 = imm.substring(11, 12)
                val imm2 = imm.substring(0, 4)
                val imm3 = imm.substring(4, 10)
                val imm4 = imm.substring(10, 11)

                return "${imm4}${imm3}${encodedOperands[1]}${encodedOperands[0]}${func3}${imm2}${imm1}${opcode}"
            }

            fun encodeInstructionU(commandName: String, encodedOperands: ArrayList<String>) : String {
                val opcode = getOpcodeByCommandName(commandName)
                return "${encodedOperands[1]}${encodedOperands[0]}${opcode}"
            }

            fun encodeInstructionUJ(commandName: String, encodedOperands: ArrayList<String>) : String {
                val opcode = getOpcodeByCommandName(commandName)
                return "${encodedOperands[1]}${encodedOperands[0]}${opcode}"
            }
        }
    }

    companion object {
        fun String.isAllDigits(): Boolean = all { it.isDigit() }

        private fun getOpcodeByCommandName(commandName: String): String {
            val commandType = commandNamesFormatMap[commandName]
            return opCodeByCommandType[commandType]!!
        }

        fun getLineParams(codeLine: String): List<String> {
            return codeLine.trim().replace("\\s+".toRegex(), " ").replace(",", "").split(" ")
        }

        fun encodeCommand(params: List<String>) : String {
            val commandName = params[0]
            val operands = params.subList(1, params.size)

            val commandType = commandNamesFormatMap[commandName]

            val encodedOperands: ArrayList<String>
            var encodedInstruction: String = ""

            when (commandType) {
                ISACommandType.R -> {
                    encodedOperands = CommandEncoder.encode_R_CommandOperands(operands)
                    encodedInstruction = InstructionEncoder.encodeInstructionR(commandName, encodedOperands)
                }
                ISACommandType.I -> {
                    encodedOperands = CommandEncoder.encode_I_CommandOperands(operands)
                    encodedInstruction = InstructionEncoder.encodeInstructionI(commandName, encodedOperands)
                }
                ISACommandType.S -> {
                    encodedOperands = CommandEncoder.encode_S_CommandOperands(operands)
                    encodedInstruction = InstructionEncoder.encodeInstructionS(commandName, encodedOperands)
                }
                ISACommandType.SB -> {
                    encodedOperands = CommandEncoder.encode_SB_CommandOperands(operands)
                    encodedInstruction = InstructionEncoder.encodeInstructionSB(commandName, encodedOperands)
                }
                ISACommandType.U -> {
                    encodedOperands = CommandEncoder.encode_U_CommandOperands(operands)
                    encodedInstruction = InstructionEncoder.encodeInstructionU(commandName, encodedOperands)
                }
                ISACommandType.UJ -> {
                    encodedOperands = CommandEncoder.encode_UJ_CommandOperands(operands)
                    encodedInstruction = InstructionEncoder.encodeInstructionUJ(commandName, encodedOperands)
                }
                null -> throw Exception("Command not found")
            }

            return encodedInstruction
        }

        fun convertToUint(encodedInstruction: String): UInt {
            return encodedInstruction.toUInt()
        }
    }
}