package new_design.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.pointer.pointerMoveFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import registers.Register
import width

private data class RegisterRectangle(val register: Register, val offset: Offset, val size: Size)

private inline fun isRegisterHovered(registerRectangle: RegisterRectangle, cursorPosition: Offset) = Rect(
    offset = registerRectangle.offset, size = registerRectangle.size
).contains(cursorPosition)

private fun getRegisterRectangles(
    registers: SnapshotStateList<Register>, mainSquareSize: Size, squareSide: Float, numberOfRegistersOnSide: Int
): List<RegisterRectangle> {
    val result = mutableListOf<RegisterRectangle>()
    val initOffset: Float = (squareSide / (numberOfRegistersOnSide * 2 + 1))
    val registerHeight = squareSide / 2.5f
    val registerWidth = squareSide / (numberOfRegistersOnSide * 2 + 1)

    //adds hover spaces on top
    for (i in 0 until 8) {
        result.add(
            RegisterRectangle(
                register = registers[i], size = Size(registerWidth, registerHeight), offset = Offset(
                    x = (mainSquareSize.width - squareSide) / 2 + initOffset + initOffset * i * 2,
                    y = (mainSquareSize.height - squareSide) / 2 - registerHeight
                )
            )
        )
    }

    //adds hover spaces on the right
    for (i in 0 until 8) {
        result.add(
            RegisterRectangle(
                register = registers[i + 8], size = Size(registerHeight, registerWidth), offset = Offset(
                    x = mainSquareSize.width - (mainSquareSize.width - squareSide) / 2,
                    y = (mainSquareSize.height - squareSide) / 2 + initOffset + initOffset * i * 2
                )
            )
        )
    }

    //adds hover spaces on bottom
    for (i in 0 until 8) {
        result.add(
            RegisterRectangle(
                register = registers[i + 16], size = Size(registerWidth, registerHeight), offset = Offset(
                    x = (mainSquareSize.width - squareSide) / 2 + squareSide - initOffset * 2 - initOffset * i * 2,
                    y = (mainSquareSize.height - squareSide) / 2 + squareSide
                )
            )
        )
    }

    //adds hover spaces on the left
    for (i in 0 until 8) {
        result.add(
            RegisterRectangle(
                register = registers[i + 24],
                size = Size(width = registerHeight, height = registerWidth),
                offset = Offset(
                    x = (mainSquareSize.width - squareSide) / 2 - registerHeight,
                    y = (mainSquareSize.height - squareSide) / 2 + squareSide - initOffset * 2 - initOffset * i * 2
                )
            )
        )
    }

    return result
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProcessorComponent(
    registers: SnapshotStateList<Register>,
    animatedColor: Color,
    currentElement: MutableState<Register>,
    isRegisterHovered: MutableState<Boolean>
) {
    val squareSide = width * 0.2f
    val numberOfRegistersOnSide = 8
    val initOffset: Float = (squareSide / (numberOfRegistersOnSide * 2 + 1))
    var tempOffsetX = initOffset
    var tempOffsetY = initOffset
    val textMeasurer = rememberTextMeasurer()
    val registerHeight = squareSide / 2.5f
    val registerWidth = squareSide / (numberOfRegistersOnSide * 2 + 1)
    val sizeCopy = Size(width = (width * 0.4).toFloat(), height = (width * 0.4).toFloat())
    val registerRectangles = getRegisterRectangles(
        registers = registers, mainSquareSize = sizeCopy, squareSide = squareSide, numberOfRegistersOnSide = 8
    )
    var searchResult: Register?


    Box(
        modifier = Modifier.size((width * 0.4).dp).pointerMoveFilter(onMove = { offset ->
            searchResult = registerRectangles.find { isRegisterHovered(it, offset) }?.register
            if (searchResult != null) {
                isRegisterHovered.value = true
                currentElement.value = searchResult as Register
            } else {
                isRegisterHovered.value = false
            }
            false
        }, onExit = {
            false
        }), contentAlignment = Alignment.Center
    ) {
        Icon(painter = painterResource("icons/V.svg"),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.fillMaxSize(0.5f).drawBehind {
                //draws a main square
                drawRect(
                    color = Color.Black,
                    size = Size(squareSide, squareSide),
                    topLeft = Offset((size.width - squareSide) / 2, (size.height - squareSide) / 2),
                )
                //draws on top
                for (i in 0 until 8) {
                    drawIntoCanvas { canvas ->
                        canvas.save()
                        canvas.translate(dx = (size.width - squareSide) / 2 + tempOffsetX, dy = -registerHeight)
                        drawRect(
                            color = if (registers[i].regValue != 0L) animatedColor else Color.White,
                            size = Size(registerWidth, registerHeight),
                        )
                        canvas.rotate(90f)
                        drawText(
                            textMeasurer = textMeasurer, registers[i].regName, topLeft = Offset(
                                registerHeight - textMeasurer.measure(registers[i].regName).size.width.toFloat() - 2f,
                                (registerWidth - textMeasurer.measure(registers[i].regName).size.height.toFloat()) / 2 - initOffset
                            )
                        )
                        drawText(
                            textMeasurer = textMeasurer, registers[i].regValue.toString(), topLeft = Offset(
                                2f,
                                (registerWidth - textMeasurer.measure(registers[i].regValue.toString()).size.height.toFloat()) / 2 - initOffset
                            )
                        )
                        canvas.restore()
                    }

                    tempOffsetX += initOffset * 2
                }

                //draws on right
                for (i in 8 until 16) {
                    drawIntoCanvas { canvas ->
                        canvas.save()
                        canvas.translate(
                            (size.width - squareSide) / 2 + squareSide, (size.height - squareSide) / 2 + tempOffsetY
                        )
                        drawRect(
                            color = if (registers[i].regValue != 0L) animatedColor else Color.White,
                            size = Size(registerHeight, registerWidth),
                        )
                        drawText(
                            textMeasurer = textMeasurer, registers[i].regName, topLeft = Offset(
                                2f,
                                (registerWidth - textMeasurer.measure(registers[i].regName).size.height.toFloat()) / 2
                            )
                        )
                        drawText(
                            textMeasurer = textMeasurer, registers[i].regValue.toString(), topLeft = Offset(
                                registerHeight - textMeasurer.measure(registers[i].regValue.toString()).size.width.toFloat() - 2f,
                                (registerWidth - textMeasurer.measure(registers[i].regValue.toString()).size.height.toFloat()) / 2
                            )
                        )
                        canvas.restore()
                    }
                    tempOffsetY += initOffset * 2
                }

                //draws on bottom
                for (i in 23 downTo 16) {
                    drawIntoCanvas { canvas ->
                        drawRect(
                            color = if (registers[i].regValue != 0L) animatedColor else Color.White,
                            size = Size(registerWidth, registerHeight),
                            topLeft = Offset(
                                (size.width - squareSide) / 2 - tempOffsetX + initOffset + squareSide,
                                (size.height - squareSide) / 2 + squareSide
                            )
                        )
                        canvas.save()
                        canvas.translate(
                            dx = (size.width - squareSide) / 2 - tempOffsetX + initOffset * 2 + squareSide,
                            dy = (size.height - squareSide) / 2 + squareSide
                        )
                        canvas.rotate(90f)
                        drawText(
                            textMeasurer = textMeasurer, registers[i].regName, topLeft = Offset(
                                2f,
                                (registerWidth - textMeasurer.measure(registers[i].regName).size.height.toFloat()) / 2
                            )
                        )
                        drawText(
                            textMeasurer = textMeasurer, registers[i].regValue.toString(), topLeft = Offset(
                                registerHeight - textMeasurer.measure(registers[i].regValue.toString()).size.width.toFloat() - 2f,
                                (registerWidth - textMeasurer.measure(registers[i].regValue.toString()).size.height.toFloat()) / 2
                            )
                        )
                        canvas.restore()
                    }
                    tempOffsetX -= initOffset * 2
                }

                //draw on left
                for (i in 24 until 32) {
                    drawIntoCanvas { canvas ->
                        canvas.save()
                        canvas.translate(
                            (size.width - squareSide) / 2 - registerHeight,
                            (size.height - squareSide) / 2 + tempOffsetY - initOffset * 2,
                        )
                        drawRect(
                            color = if (registers[i].regValue != 0L) animatedColor else Color.White,
                            size = Size(registerHeight, registerWidth),
                        )
                        drawText(
                            textMeasurer = textMeasurer, registers[i].regName, topLeft = Offset(
                                registerHeight - textMeasurer.measure(registers[i].regName).size.width.toFloat() - 2f,
                                (registerWidth - textMeasurer.measure(registers[i].regName).size.height.toFloat()) / 2
                            )
                        )

                        drawText(
                            textMeasurer = textMeasurer, registers[i].regValue.toString(), topLeft = Offset(
                                2f,
                                (registerWidth - textMeasurer.measure(registers[i].regValue.toString()).size.height.toFloat()) / 2
                            )
                        )
                        canvas.restore()
                    }
                    tempOffsetY -= initOffset * 2
                }
            })
    }
}

