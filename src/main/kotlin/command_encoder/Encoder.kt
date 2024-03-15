package command_encoder

import commandNamesFormatMap

class Encoder {
    fun parseCodeLine(codeline: String): List<String> {
        return codeline.replace(",", "").split(" ")
    }

    fun getOpcode(commandName: String): UInt {
        return commandNamesFormatMap[commandName]?.opcode ?: throw Exception("Command not found")
    }

    fun encodeRegisters(regs: List<String>): List<UInt> {
        val encodedRegs: MutableList<UInt> = List(regs.size) { 0u }.toMutableList()
        for (i in 0..regs.size) {
            encodedRegs[i] = "0b${regs[i].slice(1..regs[i].length).toUInt().toString(2).padStart(5, '0')}u".toUInt()
        }
        return encodedRegs
    }
}