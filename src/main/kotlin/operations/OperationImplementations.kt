package operations

import ExpressionParser
import ThreeArgumentsContainer
import androidx.compose.runtime.snapshots.SnapshotStateList
import registers.Register
import sys.Status


class OperationImplementations {
    companion object {
        private val expressionParser = ExpressionParser()
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
    }

}