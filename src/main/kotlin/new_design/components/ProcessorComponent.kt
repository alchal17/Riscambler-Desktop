package new_design.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.res.loadSvgPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.dp
import registers.Register
import width
import java.io.File
import javax.imageio.ImageIO

@Composable
fun ProcessorComponent(registers: SnapshotStateList<Register>, animatedColor: Color) {
    val squareSide = width * 0.2f
    val numberOfRegistersOnSide = 8
    var initOffset: Float = (squareSide / (numberOfRegistersOnSide * 2 + 1))
    var tempOffsetX = initOffset
    var tempOffsetY = initOffset
    val textMeasurer = rememberTextMeasurer()
    val registerHeight = squareSide / 4f
    val registerWidth = squareSide / (numberOfRegistersOnSide * 2 + 1)
    Box(
        modifier = Modifier.size((width * 0.4).dp), contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource("icons/V.svg"),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.fillMaxSize(0.5f).drawBehind { //draws a main square
                drawRect(
                    color = Color.Black,
                    size = Size(squareSide, squareSide),
                    topLeft = Offset((size.width - squareSide) / 2, (size.height - squareSide) / 2),
                )
                //draws on top
                for (i in 0 until 8) {
                    drawIntoCanvas { canvas ->
                        canvas.save()
                        canvas.translate((size.width - squareSide) / 2 + tempOffsetX, -registerHeight)
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
                        canvas.restore()
                    }
                    tempOffsetY += initOffset * 2
                }

                //draws on bottom
                for (i in 16 until 24) {
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
                        canvas.restore()
                    }
                    tempOffsetX -= initOffset * 2
                }

                //draw on left
                for (i in 24 until 32) {
                    drawIntoCanvas { canvas ->
                        canvas.save()
                        canvas.translate(
                            (size.width - squareSide) / 2 - squareSide / 4,
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
                        canvas.restore()
                    }
                    tempOffsetY -= initOffset * 2
                }
            })
    }
}
