package registers

class Register(regName: String, regAltName: String, regData: Long = 0) {
    private val _regName: String = regName
    private val _regAltName: String = regAltName
    private var _regValue: Long =
        if (regName == "x0" || regAltName == "zero") 0 else regData //min: 0, max: 2^32 - 1(4294967295)
    private val maxValue = 4294967295
    private val minValue = 0

    val regName: String
        get() = _regName
    val regAltName: String
        get() = _regAltName
    var regValue: Long
        get() = _regValue
        set(value) {
            if (regName != "x0" && regAltName != "zero") {
                _regValue = value
                if (_regValue > maxValue){
                    _regValue %= maxValue
                }
            }
        }

    operator fun plus(other: Register): Register {
        return Register(regName, regAltName, _regValue + other.regValue)
    }

    operator fun minus(other: Register): Register {
        return Register(regName, regAltName, _regValue - other.regValue)
    }

    operator fun plusAssign(other: Register) {
        regValue += other.regValue
    }

    operator fun minusAssign(other: Register) {
        regValue -= other.regValue
    }
}