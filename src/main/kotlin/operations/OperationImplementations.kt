package operations

import ExpressionParser
import ThreeArgumentsContainer
import androidx.compose.runtime.snapshots.SnapshotStateList
import constants_enums.Instruction
import registers.Register
import registers.RegistersViewModel
import sys.CodeExecutionStatus


class OperationImplementations {
    companion object {
        private val expressionParser = ExpressionParser()

        //  R type commands
        fun ADD(params: List<String>, line: Int): CodeExecutionStatus {
            val pair: Pair<ThreeArgumentsContainer?, CodeExecutionStatus> = expressionParser.parseRegRegReg(
                params = params,
                line = line
            )
            val status = pair.second
            val argumentsContainer = pair.first
            if (status is CodeExecutionStatus.Success && argumentsContainer != null) {
                val firstRegIndex = argumentsContainer.param1.toInt()
                val secondRegIndex = argumentsContainer.param2.toInt()
                val thirdRegIndex = argumentsContainer.param3.toInt()
                RegistersViewModel.registers[firstRegIndex].regValue =
                    RegistersViewModel.registers[secondRegIndex].regValue + RegistersViewModel.registers[thirdRegIndex].regValue
            }
            return status
        }

        fun SUB(params: List<String>, line: Int): CodeExecutionStatus {
            val pair: Pair<ThreeArgumentsContainer?, CodeExecutionStatus> = expressionParser.parseRegRegReg(
                params = params,
                line = line
            )
            val status = pair.second
            val argumentsContainer = pair.first
            if (status is CodeExecutionStatus.Success && argumentsContainer != null) {
                val firstRegIndex = argumentsContainer.param1.toInt()
                val secondRegIndex = argumentsContainer.param2.toInt()
                val thirdRegIndex = argumentsContainer.param3.toInt()
                RegistersViewModel.registers[firstRegIndex].regValue =
                    RegistersViewModel.registers[secondRegIndex].regValue - RegistersViewModel.registers[thirdRegIndex].regValue
            }
            return status
        }

        fun SLT(params: List<String>, line: Int): CodeExecutionStatus {
            val pair: Pair<ThreeArgumentsContainer?, CodeExecutionStatus> = expressionParser.parseRegRegReg(
                params = params,
                line = line
            )
            val status = pair.second
            val argumentsContainer = pair.first
            if (status is CodeExecutionStatus.Success && argumentsContainer != null) {
                val firstRegIndex = argumentsContainer.param1.toInt()
                val secondRegIndex = argumentsContainer.param2.toInt()
                val thirdRegIndex = argumentsContainer.param3.toInt()

                RegistersViewModel.registers[firstRegIndex].regValue =
                    if (RegistersViewModel.registers[secondRegIndex].regValue < RegistersViewModel.registers[thirdRegIndex].regValue) 1 else 0
            }
            return status
        }

        fun SLTU(params: List<String>, line: Int): CodeExecutionStatus {
            val pair: Pair<ThreeArgumentsContainer?, CodeExecutionStatus> = expressionParser.parseRegRegReg(
                params = params,
                line = line
            )
            val status = pair.second
            val argumentsContainer = pair.first
            if (status is CodeExecutionStatus.Success && argumentsContainer != null) {
                val firstRegIndex = argumentsContainer.param1.toInt()
                val secondRegIndex = argumentsContainer.param2.toInt()
                val thirdRegIndex = argumentsContainer.param3.toInt()

                RegistersViewModel.registers[firstRegIndex].regValue =
                    if (RegistersViewModel.registers[secondRegIndex].regValue.toUInt() < RegistersViewModel.registers[thirdRegIndex].regValue.toUInt()) 1 else 0
            }
            return status
        }

        fun AND(params: List<String>, line: Int): CodeExecutionStatus {
            val pair: Pair<ThreeArgumentsContainer?, CodeExecutionStatus> = expressionParser.parseRegRegReg(
                params = params,
                line = line
            )
            val status = pair.second
            val argumentsContainer = pair.first
            if (status is CodeExecutionStatus.Success && argumentsContainer != null) {
                val firstRegIndex = argumentsContainer.param1.toInt()
                val secondRegIndex = argumentsContainer.param2.toInt()
                val thirdRegIndex = argumentsContainer.param3.toInt()

                RegistersViewModel.registers[firstRegIndex].regValue =
                    RegistersViewModel.registers[secondRegIndex].regValue and RegistersViewModel.registers[thirdRegIndex].regValue
            }
            return status
        }

        fun OR(params: List<String>, line: Int): CodeExecutionStatus {
            val pair: Pair<ThreeArgumentsContainer?, CodeExecutionStatus> = expressionParser.parseRegRegReg(
                params = params,
                line = line
            )
            val status = pair.second
            val argumentsContainer = pair.first
            if (status is CodeExecutionStatus.Success && argumentsContainer != null) {
                val firstRegIndex = argumentsContainer.param1.toInt()
                val secondRegIndex = argumentsContainer.param2.toInt()
                val thirdRegIndex = argumentsContainer.param3.toInt()

                RegistersViewModel.registers[firstRegIndex].regValue =
                    RegistersViewModel.registers[secondRegIndex].regValue or RegistersViewModel.registers[thirdRegIndex].regValue
            }
            return status
        }

        fun XOR(params: List<String>, line: Int): CodeExecutionStatus {
            val pair: Pair<ThreeArgumentsContainer?, CodeExecutionStatus> = expressionParser.parseRegRegReg(
                params = params,
                line = line
            )
            val status = pair.second
            val argumentsContainer = pair.first
            if (status is CodeExecutionStatus.Success && argumentsContainer != null) {
                val firstRegIndex = argumentsContainer.param1.toInt()
                val secondRegIndex = argumentsContainer.param2.toInt()
                val thirdRegIndex = argumentsContainer.param3.toInt()

                RegistersViewModel.registers[firstRegIndex].regValue =
                    RegistersViewModel.registers[secondRegIndex].regValue xor RegistersViewModel.registers[thirdRegIndex].regValue
            }
            return status
        }

        fun SLL(params: List<String>, line: Int): CodeExecutionStatus {
            val pair: Pair<ThreeArgumentsContainer?, CodeExecutionStatus> = expressionParser.parseRegRegReg(
                params = params,
                line = line
            )
            val status = pair.second
            val argumentsContainer = pair.first
            if (status is CodeExecutionStatus.Success && argumentsContainer != null) {
                val firstRegIndex = argumentsContainer.param1.toInt()
                val secondRegIndex = argumentsContainer.param2.toInt()
                val thirdRegIndex = argumentsContainer.param3.toInt()

                RegistersViewModel.registers[firstRegIndex].regValue =
                    RegistersViewModel.registers[secondRegIndex].regValue shl RegistersViewModel.registers[thirdRegIndex].regValue.toInt()
            }
            return status
        }

        fun SRL(params: List<String>, line: Int): CodeExecutionStatus {
            val pair: Pair<ThreeArgumentsContainer?, CodeExecutionStatus> = expressionParser.parseRegRegReg(
                params = params,
                line = line
            )
            val status = pair.second
            val argumentsContainer = pair.first
            if (status is CodeExecutionStatus.Success && argumentsContainer != null) {
                val firstRegIndex = argumentsContainer.param1.toInt()
                val secondRegIndex = argumentsContainer.param2.toInt()
                val thirdRegIndex = argumentsContainer.param3.toInt()

                RegistersViewModel.registers[firstRegIndex].regValue =
                    RegistersViewModel.registers[secondRegIndex].regValue ushr RegistersViewModel.registers[thirdRegIndex].regValue.toInt()
            }
            return status
        }

        fun SRA(params: List<String>, line: Int): CodeExecutionStatus {
            val pair: Pair<ThreeArgumentsContainer?, CodeExecutionStatus> = expressionParser.parseRegRegReg(
                params = params,
                line = line
            )
            val status = pair.second
            val argumentsContainer = pair.first
            if (status is CodeExecutionStatus.Success && argumentsContainer != null) {
                val firstRegIndex = argumentsContainer.param1.toInt()
                val secondRegIndex = argumentsContainer.param2.toInt()
                val thirdRegIndex = argumentsContainer.param3.toInt()

                RegistersViewModel.registers[firstRegIndex].regValue =
                    RegistersViewModel.registers[secondRegIndex].regValue shr RegistersViewModel.registers[thirdRegIndex].regValue.toInt()
            }
            return status
        }

        //  I type commands
        fun ADDI(params: List<String>, line: Int): CodeExecutionStatus {
            val pair: Pair<ThreeArgumentsContainer?, CodeExecutionStatus> = expressionParser.parseRegRegInt(
                params = params,
                line = line
            )
            val status = pair.second
            val argumentsContainer = pair.first
            if (status is CodeExecutionStatus.Success && argumentsContainer != null) {
                val firstRegIndex = argumentsContainer.param1.toInt()
                val secondRegIndex = argumentsContainer.param2.toInt()
                val int = argumentsContainer.param3.toInt()
                RegistersViewModel.registers[firstRegIndex].regValue +=
                    RegistersViewModel.registers[secondRegIndex].regValue + int
            }
            return status
        }

        fun SLTI(params: List<String>, line: Int): CodeExecutionStatus {
            val pair: Pair<ThreeArgumentsContainer?, CodeExecutionStatus> = expressionParser.parseRegRegInt(
                params = params,
                line = line
            )
            val status = pair.second
            val argumentsContainer = pair.first
            if (status is CodeExecutionStatus.Success && argumentsContainer != null) {
                val firstRegIndex = argumentsContainer.param1.toInt()
                val secondRegIndex = argumentsContainer.param2.toInt()
                val int = argumentsContainer.param3.toInt()
                RegistersViewModel.registers[firstRegIndex].regValue =
                    if (RegistersViewModel.registers[secondRegIndex].regValue < int) 1 else 0
            }
            return status
        }
        //  S type commands
//z

        //  SB type commands

        //  U type commands

        //  UJ type commands

    }
}