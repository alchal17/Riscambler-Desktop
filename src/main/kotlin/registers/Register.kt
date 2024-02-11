package registers

abstract class Register(regName: String, regAltName: String) {
    protected val _regName: String = regName
    protected val _regAltName: String = regAltName

    val regName: String
        get() = _regName
    val regAltName: String
        get() = _regAltName
}