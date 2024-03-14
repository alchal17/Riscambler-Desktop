package new_design.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.rotate
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import width

@Composable
fun ProcessorComponent() {
    val squareSide = width * 0.2f
    val numberOfRegistersOnSide = 8
    var initOffset: Float = (squareSide / (numberOfRegistersOnSide * 2 + 1))
    var tempOffsetX = initOffset
    var tempOffsetY = initOffset
    val textMeasurer = rememberTextMeasurer()
    Box(
        modifier = Modifier.size((width * 0.4).dp), contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            //draws a main square
            drawRect(
                color = Color.Black,
                size = Size(squareSide, squareSide),
                topLeft = Offset((size.width - squareSide) / 2, (size.height - squareSide) / 2),
            )
            //draws on top
            repeat(numberOfRegistersOnSide) {
                drawIntoCanvas { canvas ->
                    canvas.save()
                    canvas.translate((size.width - squareSide) / 2 + tempOffsetX, squareSide / 4f)
                    drawRect(
                        color = Color.White,
                        size = Size(squareSide / (numberOfRegistersOnSide * 2 + 1), squareSide / 4),
                    )
                    canvas.rotate(90f)
                    drawText(
                        textMeasurer = textMeasurer, "AAAA", topLeft = Offset(
                            squareSide / 4 - textMeasurer.measure("AAAA").size.width.toFloat() - 2f,
                            (squareSide / (numberOfRegistersOnSide * 2 + 1) - textMeasurer.measure("AAAA").size.height.toFloat()) / 2 - initOffset
                        )
                    )
                    canvas.restore()
                }

                tempOffsetX += initOffset * 2
            }
            //draws on right
            repeat(numberOfRegistersOnSide) {
                drawIntoCanvas { canvas ->
                    canvas.save()
                    canvas.translate(
                        (size.width - squareSide) / 2 + squareSide, (size.height - squareSide) / 2 + tempOffsetY
                    )
                    drawRect(
                        color = Color.White,
                        size = Size(squareSide / 4, squareSide / (numberOfRegistersOnSide * 2 + 1)),
                    )
                    drawText(
                        textMeasurer = textMeasurer, "AAAA", topLeft = Offset(
                            2f,
                            (squareSide / (numberOfRegistersOnSide * 2 + 1) - textMeasurer.measure("AAAA").size.height.toFloat()) / 2
                        )
                    )
                    canvas.restore()
                }
                tempOffsetY += initOffset * 2
            }
            //draws on bottom
            repeat(numberOfRegistersOnSide) {
                drawIntoCanvas { canvas ->
                    drawRect(
                        color = Color.White,
                        size = Size(squareSide / (numberOfRegistersOnSide * 2 + 1), squareSide / 4),
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
                        textMeasurer = textMeasurer, "AAAA", topLeft = Offset(
                            2f,
                            (squareSide / (numberOfRegistersOnSide * 2 + 1) - textMeasurer.measure("AAAA").size.height.toFloat()) / 2
                        )
                    )
                    canvas.restore()
                }
                tempOffsetX -= initOffset * 2
            }

            //draw on left
            repeat(numberOfRegistersOnSide) {
                drawIntoCanvas { canvas ->
                    canvas.save()
                    canvas.translate(
                        (size.width - squareSide) / 2 - squareSide / 4,
                        (size.height - squareSide) / 2 + tempOffsetY - initOffset * 2,
                    )
                    drawRect(
                        color = Color.White,
                        size = Size(squareSide / 4, squareSide / (numberOfRegistersOnSide * 2 + 1)),
                    )
                    drawText(
                        textMeasurer = textMeasurer, "AAAA", topLeft = Offset(
                            squareSide / 4 - textMeasurer.measure("AAAA").size.width.toFloat() - 2f,
                            (squareSide / (numberOfRegistersOnSide * 2 + 1) - textMeasurer.measure("AAAA").size.height.toFloat()) / 2
                        )
                    )
                    canvas.restore()
                }
                tempOffsetY -= initOffset * 2
            }
        }
    }
}
