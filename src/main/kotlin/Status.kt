sealed class Status {
    data object Success : Status()
    data class Error(var errorMessage: String): Status()
}