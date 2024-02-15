package operations

import androidx.compose.runtime.snapshots.SnapshotStateList
import registers.Register

data class Operation(val type: OpType, val operationFun: (SnapshotStateList<Register>, List<String>) -> Unit)