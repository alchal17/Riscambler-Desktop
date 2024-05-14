import operations.LineData

class MnemonicProvider {
    private val commandsExplanationsMap: Map<String, (List<String>, Int) -> String> =
        mapOf("ADDI" to this::explainADDI, "ADD" to this::explainADD)

    private fun splitCodeLine(codeLine: String): Pair<String, List<String>> {
        val splitLine = codeLine.trim().split(" ")
        var copiedLine = codeLine
        val command = splitLine[0]
        copiedLine = copiedLine.replace(command, "").replace(" ", "")
        val operands = copiedLine.split(",")
        return Pair(command, operands)
    }

    fun processCode(code: String): List<String> {
        val result = mutableListOf<String>()
        val lines = code.split("\n")
        var parsedString: Pair<String, List<String>>
        var requiredFunction: ((List<String>, Int) -> String)?
        var tempResult: String?
        lines.forEachIndexed { index, line ->
            if (line.isEmpty()) {
                result.add("Line ${index + 1} is empty.")
            } else {
                parsedString = splitCodeLine(line)
                requiredFunction = commandsExplanationsMap[parsedString.first.uppercase()]
                tempResult = requiredFunction?.invoke(parsedString.second, index + 1)
                if (tempResult != null) {
                    result.add(tempResult ?: "")
                } else {
                    result.add("Line ${index + 1}: command '${parsedString.first}' not found.")
                }
            }
        }
        return result
    }

    private fun explainADDI(values: List<String>, line: Int): String {
        if (values.size != 3) {
            return "Line $line: Wrong number of operands."
        }
        return "Line $line: Increasing the value of ${values[0]} by the sum of content inside ${values[1]} and immediate ${values[2]}."
    }

    private fun explainADD(values: List<String>, line: Int): String {
        if (values.size != 3) {
            return "Line $line: Wrong number of operands."
        }
        return "Line $line: Increasing the value of ${values[0]} by the sum of content inside ${values[1]} and content inside ${values[2]}."
    }

}