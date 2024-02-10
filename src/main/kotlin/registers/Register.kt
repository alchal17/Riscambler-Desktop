package registers

abstract class Register {
    protected val regName: String
    protected val regAltName: String

    constructor(regName: String, regAltName: String) {
        this.regName = regName
        this.regAltName = regAltName
    }
}