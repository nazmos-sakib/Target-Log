package com.example.targetlog.main_activity.screens.common_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun DailyActivity(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement  = Arrangement.SpaceBetween,
        verticalAlignment  = Alignment.Top,
    ) {
        Row(
            horizontalArrangement  = Arrangement.Start,
            verticalAlignment  = Alignment.CenterVertically,
        ) {
            DateBoxButton()
            Column(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment  = Alignment.Start,
            ) {
                Text(text = "FREESTYLE", color =  Color.White)
                Text(text = "RIGHT", color =  Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }

        }
        Row(
            horizontalArrangement  = Arrangement.Start,
            verticalAlignment  = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.padding(10.dp,0.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment  = Alignment.Start,
            ) {
                Text(text = "24", color =  Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(text = "Shots", color =  Color.White,fontSize = 10.sp)
            }

            Column(
                modifier = Modifier.padding(10.dp,0.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment  = Alignment.Start,
            ) {
                Text(text = "91", color =  Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(text = "Top", color =  Color.White,fontSize = 10.sp)
            }
            HexagonThumbsUP(tintColor = Color.White)

        }
    }
}