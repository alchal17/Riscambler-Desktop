package operations

import ExpressionParser
import ThreeArgumentsContainer
import androidx.compose.runtime.snapshots.SnapshotStateList
import registers.Register
import sys.Status
import memory


class OperationImplementations {
    companion object {
        private val expressionParser = ExpressionParser()
        //  R type commands
        fun ADD(params: List<String>, registers: SnapshotStateList<Register>, line: Int): Status {
            val pair: Pair<ThreeArgumentsContainer?, Status> = expressionParser.parseRegRegReg(
                params = params,
                registers = registers,
                line = line
            )
            val status = pair.second
            val argumentsContainer = pair.first
            if (status is Status.Success && argumentsContainer != null) {
                val firstRegIndex = argumentsContainer.param1.toInt()
                val secondRegIndex = argumentsContainer.param2.toInt()
                val thirdRegIndex = argumentsContainer.param3.toInt()
                registers[firstRegIndex].regValue =
                    registers[secondRegIndex].regValue + registers[thirdRegIndex].regValue
            }
            return status
        }

        fun SUB(params: List<String>, registers: SnapshotStateList<Register>, line: Int): Status {
            val pair: Pair<ThreeArgumentsContainer?, Status> = expressionParser.parseRegRegReg(
                params = params,
                registers = registers,
                line = line
            )
            val status = pair.second
            val argumentsContainer = pair.first
            if (status is Status.Success && argumentsContainer != null) {
                val firstRegIndex = argumentsContainer.param1.toInt()
                val secondRegIndex = argumentsContainer.param2.toInt()
                val thirdRegIndex = argumentsContainer.param3.toInt()
                registers[firstRegIndex].regValue =
                    registers[secondRegIndex].regValue - registers[thirdRegIndex].regValue
            }
            return status
        }

        fun SLT(params: List<String>, registers: SnapshotStateList<Register>, line: Int): Status {
            val pair: Pair<ThreeArgumentsContainer?, Status> = expressionParser.parseRegRegReg(
                params = params,
                registers = registers,
                line = line
            )
            val status = pair.second
            val argumentsContainer = pair.first
            if (status is Status.Success && argumentsContainer != null) {
                val firstRegIndex = argumentsContainer.param1.toInt()
                val secondRegIndex = argumentsContainer.param2.toInt()
                val thirdRegIndex = argumentsContainer.param3.toInt()

                registers[firstRegIndex].regValue =
                    if (registers[secondRegIndex].regValue < registers[thirdRegIndex].regValue) 1 else 0
            }
            return status
        }

        fun SLTU(params: List<String>, registers: SnapshotStateList<Register>, line: Int): Status {
            val pair: Pair<ThreeArgumentsContainer?, Status> = expressionParser.parseRegRegReg(
                params = params,
                registers = registers,
                line = line
            )
            val status = pair.second
            val argumentsContainer = pair.first
            if (status is Status.Success && argumentsContainer != null) {
                val firstRegIndex = argumentsContainer.param1.toInt()
                val secondRegIndex = argumentsContainer.param2.toInt()
                val thirdRegIndex = argumentsContainer.param3.toInt()

                registers[firstRegIndex].regValue =
                    if (registers[secondRegIndex].regValue.toUInt() < registers[thirdRegIndex].regValue.toUInt()) 1 else 0
            }
            return status
        }

        fun AND(params: List<String>, registers: SnapshotStateList<Register>, line: Int): Status {
            val pair: Pair<ThreeArgumentsContainer?, Status> = expressionParser.parseRegRegReg(
                params = params,
                registers = registers,
                line = line
            )
            val status = pair.second
            val argumentsContainer = pair.first
            if (status is Status.Success && argumentsContainer != null) {
                val firstRegIndex = argumentsContainer.param1.toInt()
                val secondRegIndex = argumentsContainer.param2.toInt()
                val thirdRegIndex = argumentsContainer.param3.toInt()

                registers[firstRegIndex].regValue =
                    registers[secondRegIndex].regValue and registers[thirdRegIndex].regValue
            }
            return status
        }

        fun OR(params: List<String>, registers: SnapshotStateList<Register>, line: Int): Status {
            val pair: Pair<ThreeArgumentsContainer?, Status> = expressionParser.parseRegRegReg(
                params = params,
                registers = registers,
                line = line
            )
            val status = pair.second
            val argumentsContainer = pair.first
            if (status is Status.Success && argumentsContainer != null) {
                val firstRegIndex = argumentsContainer.param1.toInt()
                val secondRegIndex = argumentsContainer.param2.toInt()
                val thirdRegIndex = argumentsContainer.param3.toInt()

                registers[firstRegIndex].regValue =
                    registers[secondRegIndex].regValue or registers[thirdRegIndex].regValue
            }
            return status
        }

        fun XOR(params: List<String>, registers: SnapshotStateList<Register>, line: Int): Status {
            val pair: Pair<ThreeArgumentsContainer?, Status> = expressionParser.parseRegRegReg(
                params = params,
                registers = registers,
                line = line
            )
            val status = pair.second
            val argumentsContainer = pair.first
            if (status is Status.Success && argumentsContainer != null) {
                val firstRegIndex = argumentsContainer.param1.toInt()
                val secondRegIndex = argumentsContainer.param2.toInt()
                val thirdRegIndex = argumentsContainer.param3.toInt()

                registers[firstRegIndex].regValue =
                    registers[secondRegIndex].regValue xor registers[thirdRegIndex].regValue
            }
            return status
        }

        fun SLL(params: List<String>, registers: SnapshotStateList<Register>, line: Int): Status {
            val pair: Pair<ThreeArgumentsContainer?, Status> = expressionParser.parseRegRegReg(
                params = params,
                registers = registers,
                line = line
            )
            val status = pair.second
            val argumentsContainer = pair.first
            if (status is Status.Success && argumentsContainer != null) {
                val firstRegIndex = argumentsContainer.param1.toInt()
                val secondRegIndex = argumentsContainer.param2.toInt()
                val thirdRegIndex = argumentsContainer.param3.toInt()

                registers[firstRegIndex].regValue =
                    registers[secondRegIndex].regValue shl registers[thirdRegIndex].regValue.toInt()
            }
            return status
        }

        fun SRL(params: List<String>, registers: SnapshotStateList<Register>, line: Int): Status {
            val pair: Pair<ThreeArgumentsContainer?, Status> = expressionParser.parseRegRegReg(
                params = params,
                registers = registers,
                line = line
            )
            val status = pair.second
            val argumentsContainer = pair.first
            if (status is Status.Success && argumentsContainer != null) {
                val firstRegIndex = argumentsContainer.param1.toInt()
                val secondRegIndex = argumentsContainer.param2.toInt()
                val thirdRegIndex = argumentsContainer.param3.toInt()

                registers[firstRegIndex].regValue =
                    registers[secondRegIndex].regValue ushr registers[thirdRegIndex].regValue.toInt()
            }
            return status
        }

        fun SRA(params: List<String>, registers: SnapshotStateList<Register>, line: Int): Status {
            val pair: Pair<ThreeArgumentsContainer?, Status> = expressionParser.parseRegRegReg(
                params = params,
                registers = registers,
                line = line
            )
            val status = pair.second
            val argumentsContainer = pair.first
            if (status is Status.Success && argumentsContainer != null) {
                val firstRegIndex = argumentsContainer.param1.toInt()
                val secondRegIndex = argumentsContainer.param2.toInt()
                val thirdRegIndex = argumentsContainer.param3.toInt()

                registers[firstRegIndex].regValue =
                    registers[secondRegIndex].regValue shr registers[thirdRegIndex].regValue.toInt()
            }
            return status
        }

        //  I type commands
        fun ADDI(params: List<String>, registers: SnapshotStateList<Register>, line: Int): Status {
            val pair: Pair<ThreeArgumentsContainer?, Status> = expressionParser.parseRegRegInt(
                params = params,
                registers = registers,
                line = line
            )
            val status = pair.second
            val argumentsContainer = pair.first
            if (status is Status.Success && argumentsContainer != null) {
                val firstRegIndex = argumentsContainer.param1.toInt()
                val secondRegIndex = argumentsContainer.param2.toInt()
                val int = argumentsContainer.param3.toInt()
                registers[firstRegIndex].regValue = registers[secondRegIndex].regValue + int
            }
            return status
        }

        fun SLTI(params: List<String>, registers: SnapshotStateList<Register>, line: Int): Status {
            val pair: Pair<ThreeArgumentsContainer?, Status> = expressionParser.parseRegRegInt(
                params = params,
                registers = registers,
                line = line
            )
            val status = pair.second
            val argumentsContainer = pair.first
            if (status is Status.Success && argumentsContainer != null) {
                val firstRegIndex = argumentsContainer.param1.toInt()
                val secondRegIndex = argumentsContainer.param2.toInt()
                val int = argumentsContainer.param3.toInt()
                registers[firstRegIndex].regValue = if (registers[secondRegIndex].regValue < int) 1 else 0
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