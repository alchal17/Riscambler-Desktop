package new_design.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
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
                topLeft = Offset((size.width - squareSide) / 2, (size.height - squareSide) / 2)
            )
            //draws on top
            repeat(numberOfRegistersOnSide) {
                drawRect(
                    color = Color.White,
                    size = Size(squareSide / (numberOfRegistersOnSide * 2 + 1), squareSide / 4),
                    topLeft = Offset((size.width - squareSide) / 2 + tempOffsetX, squareSide / 4f)
                )
                tempOffsetX += initOffset * 2
            }
            //draws on right
            repeat(numberOfRegistersOnSide) {
                drawRect(
                    color = Color.White,
                    size = Size(squareSide / 4, squareSide / (numberOfRegistersOnSide * 2 + 1)),
                    topLeft = Offset(
                        (size.width - squareSide) / 2 + squareSide, (size.height - squareSide) / 2 + tempOffsetY
                    )
                )
                drawText(
                    textMeasurer = textMeasurer, "AAAA", topLeft = Offset(
                        (size.width - squareSide) / 2 + squareSide, (size.height - squareSide) / 2 + tempOffsetY
                    )
                )
                tempOffsetY += initOffset * 2
            }
            //draws on bottom
            repeat(numberOfRegistersOnSide) {
                drawRect(
                    color = Color.White,
                    size = Size(squareSide / (numberOfRegistersOnSide * 2 + 1), squareSide / 4),
                    topLeft = Offset(
                        (size.width - squareSide) / 2 - tempOffsetX + initOffset + squareSide,
                        (size.height - squareSide) / 2 + squareSide
                    )
                )
                drawText(
                    textMeasurer = textMeasurer, "AAAA", topLeft = Offset(
                        (size.width - squareSide) / 2 - tempOffsetX + initOffset + squareSide,
                        (size.height - squareSide) / 2 + squareSide
                    ), style = TextStyle(color = Color.Green)
                )



                tempOffsetX -= initOffset * 2
            }
            //draw on left
            repeat(numberOfRegistersOnSide) {
                drawRect(
                    color = Color.White,
                    size = Size(squareSide / 4, squareSide / (numberOfRegistersOnSide * 2 + 1)),
                    topLeft = Offset(
                        (size.width - squareSide) / 4, (size.height - squareSide) / 2 + tempOffsetY - initOffset * 2
                    )
                )
                tempOffsetY -= initOffset * 2
            }
        }
    }
}
