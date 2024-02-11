package registers

abstract class Register(regName: String, regAltName: String) {
    private val _regName: String = regName
    private val _regAltName: String = regAltName

    val regName: String
        get() = _regName
    val regAltName: String
        get() = _regAltName
}