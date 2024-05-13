package sys

import constants_enums.Instruction

sealed class EncodingStatus {
    data object Success: EncodingStatus()
    data class Error (val errorMessage: String) : EncodingStatus()
}