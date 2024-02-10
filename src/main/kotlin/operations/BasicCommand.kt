package operations

import registers.Register

class BasicCommand(private val opName: String, private val opDescr: String, protected val cmdType: CommandType, private val  opOperands: List<Register>) {
}