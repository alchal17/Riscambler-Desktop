package sys

import constants_enums.Instruction

sealed class LineCheckStatus {
    data class Success(val instruction: Instruction) : LineCheckStatus()
    data class Error(val message: String) : LineCheckStatus()
}