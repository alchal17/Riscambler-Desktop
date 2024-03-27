package constants_enums

enum class ISACommandType(val opcode: String) {
    R("0110011"),
    I("0010011"),
    S("0100011"),
    SB("1100011"),
    U("0110111"),
    UJ("1101111")
}