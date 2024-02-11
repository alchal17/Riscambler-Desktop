package registers

class FloatRegister(regName: String, regAltName: String, regData: Double = 0.0) : Register(regName, regAltName) {
    private var _regData: Double = regData
    var regData: Double
        get() = _regData
        set(value) {
            _regData = value
        }

    override fun getValue(): String {
        return _regData.toString()
    }

    override fun toString() = "{regName: $regName; regAltName: $regAltName; regData: $_regData"
}