package com.example.targetlog.main_activity.screens.bottom_nav_home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.targetlog.R
import com.example.targetlog.ui.theme.GreenLight


@Preview
@Composable
fun Banner(){
    Box(
        modifier = Modifier ,
        contentAlignment = Alignment.Center
    ){
        Row {
            Icon(
                modifier = Modifier
                    .padding(10.dp, 0.dp)
                    .size(80.dp),
                painter = painterResource(id = R.drawable.home_2),
                contentDescription = "fire",
                tint = GreenLight
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "SHOOT",
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "NOW",
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
