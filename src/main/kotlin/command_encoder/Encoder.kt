package command_encoder

import commandNamesFormatMap
import funct7Map
import funct3Map


fun splitLine(codeLine: String): List<String> {
    return (codeLine.trim().replace(",", "")).split(" ");
}

fun encodeName(commandName: String): String {
    return funct7Map[commandName] + funct3Map[commandName] + (commandNamesFormatMap[commandName]?.opcode
        ?: "Command not found")
}

fun encodeRegs(regs: List<String>): List<String> {
    val encodedRegs = mutableListOf<String>()
    for (reg in regs) {
        val encodedReg = Integer.toBinaryString(reg.substring(1).toInt())
        val paddedReg = "0".repeat(5 - encodedReg.length) + encodedReg
        encodedRegs.add(paddedReg.reversed())
    }
    return encodedRegs
}