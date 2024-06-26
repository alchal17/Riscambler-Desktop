import androidx.compose.runtime.snapshots.SnapshotStateList
import registers.Register
import registers.RegistersViewModel
import sys.CodeExecutionStatus

class ExpressionParser {
    //returns three indexes of registers
    fun parseRegRegReg(
        params: List<String>,
        line: Int
    ): Pair<ThreeArgumentsContainer?, CodeExecutionStatus> {
        var status: CodeExecutionStatus = CodeExecutionStatus.Success
        var argumentsContainer: ThreeArgumentsContainer? = null
        if (params.size != 3) {
            status = CodeExecutionStatus.Error("Line $line: error. Expected 3 arguments, but got ${params.size}.")
        } else {
            val param1 = params[0]
            val param2 = params[1]
            val param3 = params[2]
            val index1 = RegistersViewModel.registers.indexOfFirst { param1 in listOf(it.regName, it.regValue) }
            val index2 = RegistersViewModel.registers.indexOfFirst { param2 in listOf(it.regName, it.regValue) }
            val index3 = RegistersViewModel.registers.indexOfFirst { param3 in listOf(it.regName, it.regValue) }
            if (listOf(index1, index2, index3).contains(-1)) {
                status = CodeExecutionStatus.Error("Line $line: error. Wrong arguments")
            } else {
                argumentsContainer = ThreeArgumentsContainer(
                    param1 = index1.toString(),
                    param2 = index2.toString(),
                    param3 = index3.toString()
                )
            }
        }
        return Pair(first = argumentsContainer, second = status)
    }

    //returns two indexes of registers and int
    fun parseRegRegInt(
        params: List<String>,
        line: Int
    ): Pair<ThreeArgumentsContainer?, CodeExecutionStatus> {
        var status: CodeExecutionStatus = CodeExecutionStatus.Success
        var argumentsContainer: ThreeArgumentsContainer? = null
        if (params.size != 3) {
            status = CodeExecutionStatus.Error("Line $line: error. Expected 3 arguments, but got ${params.size}.")
        } else {
            val param1 = params[0]
            val param2 = params[1]
            val param3 = params[2]
            val index1 = RegistersViewModel.registers.indexOfFirst { param1 in listOf(it.regName, it.regValue) }
            val index2 = RegistersViewModel.registers.indexOfFirst { param2 in listOf(it.regName, it.regValue) }
            val int = param3.toIntOrNull()
            if (listOf(index1, index2).contains(-1) || int == null) {
                status = CodeExecutionStatus.Error("Line $line: error. Wrong arguments")
            } else {
                argumentsContainer = ThreeArgumentsContainer(
                    param1 = index1.toString(),
                    param2 = index2.toString(),
                    param3 = param3
                )
            }
        }
        return Pair(first = argumentsContainer, second = status)
    }
}
