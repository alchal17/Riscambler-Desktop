package sys

sealed class Status {
    object Success : Status()
    data class Error (val errorMessage: String) : Status()
}