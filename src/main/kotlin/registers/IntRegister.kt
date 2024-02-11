package registers

class IntRegister(regName: String, regAltName: String, regData: Int = 0) : Register(regName, regAltName) {
    private var _regData: Int = regData
    var regData: Int
        get() = _regData
        set(value) {
            _regData = value
        }

    override fun toString() = "{regName: $regName; regAltName: $regAltName; regData: $_regData"
}