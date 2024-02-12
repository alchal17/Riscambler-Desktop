package operations

import registers.Register

import operations.op_types.ArithmeticOps
import operations.op_types.ComparisonOps
import operations.op_types.LogicalOps

import operations.op_functions.math.Math

sealed class Operation (
    val opName: String,
    val opDescr: String,
    val instType: CommandType
) {
    abstract fun execute(operands: List<Register>)

    @Suppress("UNCHECKED_CAST")
    class ArithmeticCmd (
        opName: String,
        opDescr: String,
        instType: CommandType,
        private val cmdType: ArithmeticOps
    ) : Operation(opName, opDescr, instType) {
        override fun execute(operands: List<Register>) {
//            when (cmdType) {
//                ArithmeticOps.ADD -> Math.IntMath().add(operands[0] as IntRegister, operands as List<IntRegister>)
//                ArithmeticOps.SUB -> Math.IntMath().sub(operands[0] as IntRegister, operands as List<IntRegister>)
//                ArithmeticOps.FADD -> Math.FloatMath().fadd(operands[0] as FloatRegister, operands as List<FloatRegister>)
//                ArithmeticOps.FSUB -> Math.FloatMath().fsub(operands[0] as FloatRegister, operands as List<FloatRegister>)
//                ArithmeticOps.MUL -> TODO()
//                ArithmeticOps.DIV -> TODO()
//                ArithmeticOps.REM -> TODO()
//                ArithmeticOps.NEG -> TODO()
//            }
        }
    }

    class LogicalCmd(
        opName: String,
        opDescr: String,
        instType: CommandType,
        private val cmdType: LogicalOps
    ) : Operation(opName, opDescr, instType) {
        override fun execute(operands: List<Register>) {
            when (cmdType) {
                LogicalOps.AND -> TODO()
                LogicalOps.OR -> TODO()
                LogicalOps.XOR -> TODO()
                LogicalOps.NOT -> TODO()
            }
        }
    }

    class ComparisonCmd(
        opName: String,
        opDescr: String,
        instType: CommandType,
        private val cmdType: ComparisonOps
    ) : Operation(opName, opDescr, instType) {
        override fun execute(operands: List<Register>) {
            when (cmdType) {
                ComparisonOps.SLT -> TODO()
                ComparisonOps.SLTU -> TODO()
                ComparisonOps.SLTI -> TODO()
                ComparisonOps.SLTIU -> TODO()
            }
        }
    }
}