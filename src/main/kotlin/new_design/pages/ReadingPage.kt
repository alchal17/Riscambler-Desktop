package new_design.pages

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import height
import kotlinx.coroutines.launch
import width

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReadingPage() {
    val sectionContents = listOf<@Composable () -> Unit>({ Text("Section1") },
        { Text("Section2") },
        { Box(modifier = Modifier.fillMaxSize().background(color = Color.Red)) })
    val pagerState = rememberPagerState(pageCount = { sectionContents.size })
    val coroutineScope = rememberCoroutineScope()
    Column {
        HorizontalPager(modifier = Modifier.fillMaxWidth().weight(1f), state = pagerState) {page ->
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Box(
                    modifier = Modifier.width((width * 0.75f).dp).fillMaxHeight(0.75f).clip(RoundedCornerShape(20))
                        .background(color = Color(39, 58, 70)), contentAlignment = Alignment.Center
                ) {
                    Box(modifier = Modifier.padding(horizontal = (width / 20).dp, vertical = (height / 20).dp)) {
                        sectionContents[page]()
                    }
                }
            }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            if (pagerState.canScrollBackward) {
                Button(onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                }) { Text("Previous") }
            } else {
                Box {}
            }
            if (pagerState.canScrollForward) {
                Button(onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }

                }) { Text("Next") }
            } else {
                Box {}
            }
        }
    }
}