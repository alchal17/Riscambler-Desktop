package operations

import registers.Register

class BasicCommand(
    private val _opName: String,
    private val _opDescr: String,
    protected val _cmdType: CommandType,
    private val _opOperands: List<Register>
) {
    val opName: String
        get() = _opName
    val opDescr: String
        get() = _opDescr
    val cmdType: CommandType
        get() = _cmdType
    val opOperands: List<Register>
        get() = _opOperands
    fun debugCommand(){
        println("${opName.toUpperCase()} : $cmdType")
        println(opDescr)
        println("Working with")
    }
}