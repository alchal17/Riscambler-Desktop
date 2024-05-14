package sys

sealed class RunStatus {
    data object Success: RunStatus()
    data class Error (val errorMessage: String) : RunStatus()
}