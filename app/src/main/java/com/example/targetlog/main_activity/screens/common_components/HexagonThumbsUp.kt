package com.example.targetlog.main_activity.screens.common_components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import com.example.targetlog.R
import com.example.targetlog.commons.RoundedPolygonShape

@Preview
@Composable
fun HexagonThumbsUP(){
    val hexagon = remember {
        RoundedPolygon(
            6,
            rounding = CornerRounding(0.2f)
        )
    }
    val clip = remember(hexagon) {
        RoundedPolygonShape(polygon = hexagon)
    }
    Box(modifier = Modifier
        .rotate(90f)
        .padding(0.dp)) {
        Box(
            modifier = Modifier
                .padding(0.dp)
                .border(2.dp, Color.Gray,clip)
                .size(50.dp)
                .padding(0.dp)
                .rotate(270f),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.outline_thumb_up_24),
                contentDescription = "follower profile pic",
                tint = Color.Gray
            )
        }
    }
}

@Composable
fun HexagonThumbsUP(tintColor: Color){
    val hexagon = remember {
        RoundedPolygon(
            6,
            rounding = CornerRounding(0.2f)
        )
    }
    val clip = remember(hexagon) {
        RoundedPolygonShape(polygon = hexagon)
    }
    Box(modifier = Modifier
        .rotate(90f)
        .padding(0.dp)) {
        Box(
            modifier = Modifier
                .padding(0.dp)
                .border(2.dp, tintColor,clip)
                .size(50.dp)
                .padding(0.dp)
                .rotate(270f),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.outline_thumb_up_24),
                contentDescription = "follower profile pic",
                tint = tintColor
            )
        }
    }
}