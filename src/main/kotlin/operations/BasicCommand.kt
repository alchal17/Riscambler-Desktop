package operations

import registers.Register

class BasicCommand(
    private val _opName: String,
    private val _opDescr: String,
    private val _cmdType: CommandType,
    private val _opOperands: List<Register>
) {
    private val opName: String
        get() = _opName
    private val opDescr: String
        get() = _opDescr
    private val cmdType: CommandType
        get() = _cmdType
    val opOperands: List<Register>
        get() = _opOperands
    fun debugCommand(){
        println("${opName.uppercase()} : $cmdType")
        println(opDescr)
        println("Working with")
    }
}