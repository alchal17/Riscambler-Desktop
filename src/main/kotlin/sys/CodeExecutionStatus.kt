package sys

sealed class CodeExecutionStatus {
    data object Success : CodeExecutionStatus()
    data class Error (val errorMessage: String) : CodeExecutionStatus()
}