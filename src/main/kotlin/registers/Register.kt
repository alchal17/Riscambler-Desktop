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
    var regData: Long
        get() = _regValue
        set(value) {
            if (regName != "x0" && regAltName != "zero") {
                _regValue = value
            }
        }

    operator fun plus(other: Register): Register {
        return Register(regName, regAltName, _regValue + other.regData)
    }

    operator fun minus(other: Register): Register {
        return Register(regName, regAltName, _regValue - other.regData)
    }

    operator fun plusAssign(other: Register) {
        regData += other.regData
        if (_regValue > maxValue){
            regData -= maxValue
        }
    }

    operator fun minusAssign(other: Register) {
        regData -= other.regData
        if (_regValue < minValue){
            regData = maxValue - other.regData
        }
    }
}