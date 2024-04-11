package constants_enums

enum class ISACommandType(val opcode: String) {
    R("0110011"),
    I("0010011"),
    S("0100011"),
    SB("1100011"),
    U("0110111"),
    UJ("1101111")
}


sealed class Instruction {
    data class RCommand(
        val opcode: String,
        val rd: String,
        val func3: String?,
        val rs1: String,
        val rs2: String,
        val func7: String?
    ) : Instruction() {}

    data class ICommand(
        val opcode: String,
        val rd: String,
        val func3: String?,
        val rs1: String,
        val immediate: String
    ) : Instruction() {}

    data class SCommand(
        val opcode: String,
        val immediate1: String,
        val func3: String?,
        val rs1: String ,
        val rs2: String,
        val immediate2: String
    ) : Instruction() {}

    data class SBCommand(
        val opcode: String,
        val immediate1: String,
        val immediate2: String,
        val func3: String?,
        val rs1: String,
        val rs2: String,
        val immediate3: String,
        val immediate4: String
    ) : Instruction() {}

    data class UCommand(
        val opcode: String,
        val rd: String,
        val immediate: String
    ) : Instruction() {}

    data class UJCommand(
        val opcode: String,
        val rd: String,
        val immediate1: String,
        val immediate2: String,
        val immediate3: String,
        val immediate4: String,
    ) : Instruction() {}
}