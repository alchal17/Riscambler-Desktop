import androidx.compose.runtime.snapshots.SnapshotStateList
import registers.Register
import sys.CodeRunner

class ExpressionParser {
    //returns three indexes of registers
    fun parseRegRegReg(
        params: List<String>,
        registers: SnapshotStateList<Register>,
        line: Int
    ): ArgumentsContainer?{
        if (params.size != 3){
            CodeRunner.status = Status.Error("")
            return null
        }
        else{
            CodeRunner.status = Status.Success
        }
        return null;
    }
}
