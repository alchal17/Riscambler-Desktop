package operations

sealed class OpType(val name: String) {
    sealed class ArithmeticOps(name: String) : OpType(name) {
        data object ADD : ArithmeticOps("ADD")
        data object ADDI : ArithmeticOps("ADDI")
        data object FADD : ArithmeticOps("FADD")
        data object SUB : ArithmeticOps("SUB")
        data object FSUB : ArithmeticOps("FSUB")
        data object MUL : ArithmeticOps("MUL")
        data object DIV : ArithmeticOps("DIV")
        data object REM : ArithmeticOps("REM")
        data object NEG : ArithmeticOps("NEG")
    }

    sealed class ControlTransferOps(name: String) : OpType(name) {
        data object JAL : ControlTransferOps("JAL")
        data object JALR : ControlTransferOps("JALR")
        data object BEQ : ControlTransferOps("BEQ")
        data object BNE : ControlTransferOps("BNE")
        data object BLT : ControlTransferOps("BLT")
        data object BGE : ControlTransferOps("BGE")
    }

    sealed class ComparisonOps(name: String) : OpType(name) {
        data object SLT : ComparisonOps("SLT")
        data object SLTU : ComparisonOps("SLTU")
        data object SLTI : ComparisonOps("SLTI")
        data object SLTIU : ComparisonOps("SLTIU")
    }

    sealed class DataTransferOps(name: String) : OpType(name) {
        data object LW : DataTransferOps("LW")
        data object SW : DataTransferOps("SW")
        data object LB : DataTransferOps("LB")
        data object SB : DataTransferOps("SB")
    }

    sealed class LogicalOps(name: String) : OpType(name) {
        data object AND : LogicalOps("AND")
        data object OR : LogicalOps("OR")
        data object XOR : LogicalOps("XOR")
        data object NOT : LogicalOps("NOT")
    }

    sealed class ShiftAndBitManipulationOps(name: String) : OpType(name) {
        data object SLL : ShiftAndBitManipulationOps("SLL")
        data object SRL : ShiftAndBitManipulationOps("SRL")
        data object SRA : ShiftAndBitManipulationOps("SRA")
        data object ROL : ShiftAndBitManipulationOps("ROL")
        data object ROR : ShiftAndBitManipulationOps("ROR")
    }
    sealed class SysInstructions(name: String) : OpType(name){
        data object ECALL : SysInstructions("ECALL")
        data object EBREAK : SysInstructions("EBREAK")
    }
}