package design.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Badge
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import screenWidth

private enum class Section {
    WARNING, ERROR
}

@Composable
fun HorizontalAnalyzer(warnings: List<String>, error: String) {
    val controlPanelWidth = screenWidth / 30
    var currentSection by remember { mutableStateOf(Section.WARNING) }
    Row(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.97f)) {
        Column(
            modifier = Modifier.fillMaxHeight().width(controlPanelWidth.dp).background(color = Color.LightGray),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.End
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().weight(1f)
                    .background(if (currentSection == Section.WARNING) Color(74, 74, 74) else Color.LightGray)
                    .clickable { currentSection = Section.WARNING },
                contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource("icons/warning-circle-svgrepo-com.svg"),
                        contentDescription = null,
                        tint = Color.Yellow,
                        modifier = Modifier.size(40.dp)
                    )
                    if (warnings.isNotEmpty()) {
                        Badge(
                            content = {
                                Box(
                                    modifier = Modifier.padding(4.dp),
                                    content = {
                                        Text(
                                            text = warnings.size.toString(),
                                            color = Color.Black
                                        )
                                    }
                                )
                            },
                            backgroundColor = Color.Yellow,
                            modifier = Modifier.align(Alignment.TopEnd).clip(shape = CircleShape)
                        )
                    }
                }

            }
            Box(
                modifier = Modifier.width(controlPanelWidth.dp).weight(1f).background(if (currentSection == Section.WARNING) Color.LightGray else Color(74, 74, 74))
                    .clickable { currentSection = Section.ERROR },
                contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource("icons/error-circle-fail-failure-disallowed-x-cross-bad-svgrepo-com.svg"),
                        contentDescription = null,
                        tint = Color.Red,
                        modifier = Modifier.size(40.dp)
                    )
                    if (error.isNotEmpty()) {
                        Badge(
                            content = {
                                Box(
                                    modifier = Modifier.padding(4.dp),
                                    content = {
                                        Text(
                                            text = "1",
                                            color = Color.Black
                                        )
                                    }
                                )
                            },
                            backgroundColor = Color.Red,
                            modifier = Modifier.align(Alignment.TopEnd).clip(shape = CircleShape)
                        )
                    }
                }

            }
        }
        Box(modifier = Modifier.weight(1f).fillMaxHeight().background(Color.Gray)) {
            when (currentSection) {
                Section.WARNING -> WarningSection(warnings)
                Section.ERROR -> ErrorSection(error)
            }
        }
    }
}
