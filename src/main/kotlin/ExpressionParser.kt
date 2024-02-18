import androidx.compose.runtime.snapshots.SnapshotStateList
import registers.Register
import sys.Status

class ExpressionParser {
    //returns three indexes of registers
    fun parseRegRegReg(
        params: List<String>,
        registers: SnapshotStateList<Register>,
        line: Int
    ): Pair<ThreeArgumentsContainer?, Status> {
        var status: Status = Status.Success
        var argumentsContainer: ThreeArgumentsContainer? = null
        if (params.size != 3) {
            status = Status.Error("Line $line: error. Expected 3 arguments, but got ${params.size}.")
        } else {
            val param1 = params[0]
            val param2 = params[1]
            val param3 = params[2]
            val reg1 = registers.find { param1 in listOf(it.regName, it.regAltName) }
            val reg2 = registers.find { param2 in listOf(it.regName, it.regAltName) }
            val reg3 = registers.find { param3 in listOf(it.regName, it.regAltName) }
            if (listOf(reg1, reg2, reg3).contains(null)) {
                status = Status.Error("Line $line: error. Wrong arguments")
            } else {
                argumentsContainer = ThreeArgumentsContainer(
                    registers.indexOf(reg1).toString(),
                    registers.indexOf(reg2).toString(),
                    registers.indexOf(reg3).toString()
                )
            }
        }
        return Pair(argumentsContainer, status)
    }

    //returns two indexes of registers and int
    fun parseRegRegInt(
        params: List<String>,
        registers: SnapshotStateList<Register>,
        line: Int
    ): Pair<ThreeArgumentsContainer?, Status> {
        var status: Status = Status.Success
        var argumentsContainer: ThreeArgumentsContainer? = null
        if (params.size != 3) {
            status = Status.Error("Line $line: error. Expected 3 arguments, but got ${params.size}.")
        } else {
            val param1 = params[0]
            val param2 = params[1]
            val param3 = params[2]
            val reg1 = registers.find { param1 in listOf(it.regName, it.regAltName) }
            val reg2 = registers.find { param2 in listOf(it.regName, it.regAltName) }
            val int = param3.toIntOrNull()
            if (listOf(reg1, reg2).contains(null) || int == null) {
                status = Status.Error("Line $line: error. Wrong arguments")
            } else {
                argumentsContainer = ThreeArgumentsContainer(
                    registers.indexOf(reg1).toString(),
                    registers.indexOf(reg2).toString(),
                    param3
                )
            }
        }
        return Pair(argumentsContainer, status)
    }
}
