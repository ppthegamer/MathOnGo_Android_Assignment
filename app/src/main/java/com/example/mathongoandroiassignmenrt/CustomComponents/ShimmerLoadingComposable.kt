package com.example.mathongoandroiassignmenrt.CustomComponents

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerRowLoading()
{
    var size by remember { mutableStateOf(IntSize.Zero) }
    val transition = rememberInfiniteTransition(label = "video shimmer effect")
    val startOffSetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(repeatMode = RepeatMode.Reverse, animation = tween(700)),
        label = "video shimmer effect"
    )
    val brush = Brush.linearGradient(
        colors = listOf(
            Color.LightGray.copy(.3f), Color.LightGray.copy(.6f), Color.LightGray.copy(.9f),
        ),
        start = Offset(startOffSetX, 0f),
        end = Offset(startOffSetX + size.width.toFloat(), size.height.toFloat())


    )
    LazyRow( horizontalArrangement = Arrangement.spacedBy(10.dp),modifier = Modifier
        .fillMaxSize()
        .onGloballyPositioned { size = it.size },content = { items(5) {
        Surface(shape = RoundedCornerShape(15.dp)) {
            Spacer(
                modifier = Modifier
                    .size(156.dp)

                    .background(brush = brush)
            )
        }
    } })
}

