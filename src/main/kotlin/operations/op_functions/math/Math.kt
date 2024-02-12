package operations.op_functions.math

import registers.Register

sealed class Math {
    class IntMath {
        fun add(dstRegister: Register, operands: List<Register>) {
            var sum: Long = 0
            for (i in 1..operands.size) {
                sum += operands[i].regData
            }
            dstRegister.regData = sum
        }

        fun sub(dstRegister: Register, operands: List<Register>) {
            var res = operands[1].regData
            for (i in 2..operands.size) {
                res -= operands[i].regData
            }
            dstRegister.regData = res
        }
    }

    class FloatMath {
        fun fadd(dstRegister: Register, operands: List<Register>) {
            var fsum: Long = 0
            for (i in 1..operands.size) {
                fsum += operands[i].regData
            }
            dstRegister.regData = fsum
        }

        fun fsub(dstRegister: Register, operands: List<Register>) {
            var fres = operands[1].regData
            for (i in 2..operands.size) {
                fres -= operands[i].regData
            }
            dstRegister.regData = fres
        }
    }
}