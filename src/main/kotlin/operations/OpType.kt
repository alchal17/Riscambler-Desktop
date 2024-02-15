package operations

sealed class OpType {
    enum class ArithmeticOps {
        ADD, FADD,
        SUB, FSUB,
        MUL,
        DIV,
        REM,
        NEG
    }
    enum class ComparisonOps {
        SLT,
        SLTU,
        SLTI,
        SLTIU
    }
    enum class ControlTransferOps {
        JAL,
        JALR,
        BEQ,
        BNE,
        BLT,
        BGE
    }
    enum class DataTransferOps {
        LW,
        SW,
        LB,
        SB
    }
    enum class LogicalOps {
        AND,
        OR,
        XOR,
        NOT
    }
    enum class ShiftAndBitManipulationOps {
        SLL,
        SRL,
        SRA,
        ROL,
        ROR
    }
    enum class SysInstructions {
        ECALL,
        EBREAK
    }
}