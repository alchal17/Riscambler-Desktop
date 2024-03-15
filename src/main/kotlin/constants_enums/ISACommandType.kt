package constants_enums

enum class ISACommandType(val opcode: UInt) {
    R(0b0110011u),
    I(0b0010011u),
    S(0b0100011u),
    SB(0b1100011u),
    U(0b0110111u),
    UJ(0b1101111u)
}