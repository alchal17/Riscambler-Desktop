package sys

sealed class Status {
    data object Success : Status()
    data class Error (val errorMessage: String) : Status()
}