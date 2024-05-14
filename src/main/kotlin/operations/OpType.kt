package operations

import androidx.compose.runtime.snapshots.SnapshotStateList
import registers.Register
import sys.CodeExecutionStatus

sealed class OpType(
    val name: String,
    val commandFunction: ((params: List<String>, line: Int) -> CodeExecutionStatus)?
) {
    sealed class ArithmeticOps(
        name: String,
        function: ((List<String>, Int) -> CodeExecutionStatus)? = null
    ) : OpType(name, function) {
        data object ADD : ArithmeticOps("ADD", OperationImplementations::ADD)       // Add
        data object ADDI : ArithmeticOps("ADDI", OperationImplementations::ADDI)    // Subtracts
        data object SUB : ArithmeticOps("SUB", OperationImplementations::SUB)       // Add immediate
        data object SLT : ArithmeticOps("SLT", OperationImplementations::SLT)       // Set less than
        data object SLTI : ArithmeticOps("SLTI", OperationImplementations::SLTI)    // Set less than immediate
        data object SLTU : ArithmeticOps("SLTU", OperationImplementations::SLTU)                                    // Set less than unsigned
        data object SLTIU : ArithmeticOps("SLTIU")                                  // Set less than immediate unsigned
        data object LUI : ArithmeticOps("LUI")                                      // Load upper immediate
        data object AUIP : ArithmeticOps("AUIP")                                    // Add upper immediate to PC
    }

    sealed class LoadStoreOps(
        name: String,
        function: ((List<String>, Int) -> CodeExecutionStatus)? = null
    ) : OpType(name, function) {
        data object LD : LoadStoreOps("LD")         // Load doubleword
        data object LW : LoadStoreOps("LW")         // Load word
        data object LH : LoadStoreOps("LH")         // Load halfword
        data object LB : LoadStoreOps("LB")         // Load byte
        data object LWU : LoadStoreOps("LWU")       // Load word unsigned
        data object LHU : LoadStoreOps("LHU")       // Load halfword unsigned
        data object LBU : LoadStoreOps("LBU")       // Load byte unsigned
        data object SD : LoadStoreOps("SD")         // Store doubleword
        data object SW : LoadStoreOps("SW")         // Store word
        data object SH : LoadStoreOps("SH")         // Store halfword
        data object SB : LoadStoreOps("SB")         // Store byte
    }

    sealed class LogicalOps(
        name: String,
        function: ((List<String>, Int) -> CodeExecutionStatus)? = null
    ) : OpType(name, function) {
        data object AND : LogicalOps("AND", OperationImplementations::AND)             // AND
        data object OR : LogicalOps("OR")               // OR
        data object XOR : LogicalOps("XOR")             // XOR
        data object ANDI : LogicalOps("ANDI")           // AND immediate
        data object ORI : LogicalOps("ORI")             // OR immediate
        data object XORI : LogicalOps("XORI")           // XOR immediate
        data object SLL : LogicalOps("SLL")             // Shift left logical
        data object SRL : LogicalOps("SRL")             // Shift right logical
        data object SRA : LogicalOps("SRA")             // Shift right arithmetic
        data object SLLI : LogicalOps("SLLI")           // Shift left logical immediate
        data object SRLI : LogicalOps("SRLI")           // Shift right logical immediate
        data object SRAI : LogicalOps("SRAI")           // Shift right arithmetic immediate
    }

    sealed class BranchingOps(
        name: String,
        function: ((List<String>, Int) -> CodeExecutionStatus)? = null
    ) : OpType(name, function) {
        data object BEQ : BranchingOps("BEQ")           // Branch equal
        data object BNE : BranchingOps("BNE")           // Branch not equal
        data object BGE : BranchingOps("BGE")           // Branch greater than or equal
        data object BGEU : BranchingOps("BGEU")         // Branch greater than or equal unsigned
        data object BLT : BranchingOps("BLT")           // Branch less than
        data object BLTU : BranchingOps("BLTU")         // Branch less than unsigned
        data object JAL : BranchingOps("JAL")           // Jump and link
        data object JALR : BranchingOps("JALR")           // Jump and link register
    }

    sealed class PseudoInstructionsOps(
        name: String,
        function: ((List<String>, Int) -> CodeExecutionStatus)? = null
    ) : OpType(name, function) {
        /*
        * Operations of type "branch if" are currently not listed.
        * To be implemented in future
        */
        data object LI : PseudoInstructionsOps("LI")            // Load immediate
        data object LA : PseudoInstructionsOps("LA")            // Load absolute address
        data object MV : PseudoInstructionsOps("MV")            // Copy register
        data object NOT : PseudoInstructionsOps("NOT")          // One's complement
        data object NEG : PseudoInstructionsOps("NEG")          // Two's complement
        data object J : PseudoInstructionsOps("J")              // Unconditional jump
        data object CALL : PseudoInstructionsOps("CALL")        // Call subroutine
        data object RET : PseudoInstructionsOps("RET")          // Return from subroutine
        data object NOP : PseudoInstructionsOps("NOP")          // No operation
    }
}