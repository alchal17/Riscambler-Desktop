package operations.op_functions.math

import registers.IntRegister
import registers.FloatRegister

sealed class Math {
    class IntMath {
        fun add(dstRegister: IntRegister, operands: List<IntRegister>) {
            var sum = 0
            for (i in 1..operands.size) {
                sum += operands[i].regData
            }
            dstRegister.regData = sum
        }

        fun sub(dstRegister: IntRegister, operands: List<IntRegister>) {
            var res = operands[1].regData
            for (i in 2..operands.size) {
                res -= operands[i].regData
            }
            dstRegister.regData = res
        }
    }

    class FloatMath {
        fun fadd(dstRegister: FloatRegister, operands: List<FloatRegister>) {
            var fsum = 0.0
            for (i in 1..operands.size) {
                fsum += operands[i].regData
            }
            dstRegister.regData = fsum
        }

        fun fsub(dstRegister: FloatRegister, operands: List<FloatRegister>) {
            var fres = operands[1].regData
            for (i in 2..operands.size) {
                fres -= operands[i].regData
            }
            dstRegister.regData = fres
        }
    }
}