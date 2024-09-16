package com.example.targetlog.main_activity.screens.common_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.targetlog.ui.theme.DarkLight
import com.example.targetlog.ui.theme.Purple97


@Composable
fun StatusCard(
    text:String,
    score:String,
    scoreColor:Color
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(DarkLight, RoundedCornerShape(8.dp))
            .padding(10.dp,10.dp,0.dp,10.dp)
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
           Box(
               modifier = Modifier
                   .size(50.dp)
                   .background(Color.Black, CircleShape)
           )
            Text(
                text = text,
                modifier = Modifier.weight(.3f) .padding(15.dp,0.dp,0.dp,0.dp),
                textAlign = TextAlign.Start,
                color = Color.White,
                fontSize = 16.sp,
                maxLines = 2
            )

            Text(
                text = score,
                modifier = Modifier.weight(.7f).padding(15.dp,0.dp),
                textAlign = TextAlign.Right,
                color = scoreColor,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                overflow = TextOverflow.Visible
            )
        }
    }
}

@Preview
@Composable
fun StatusCardPreview(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(DarkLight, RoundedCornerShape(8.dp))
            .padding(10.dp)
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
           Box(
               modifier = Modifier
                   .size(50.dp)
                   .background(Color.Black, CircleShape)
           )
            Text(
                text = "Average Weekly Workout going good",
                modifier = Modifier.width(200.dp).padding(15.dp,0.dp),
                textAlign = TextAlign.Start,
                color = Color.White,
                fontSize = 16.sp,
                maxLines = 2
            )

            Text(
                text = "99",
                modifier = Modifier.width(200.dp).padding(15.dp,0.dp),
                textAlign = TextAlign.Right,
                color = Purple97,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
            )
        }
    }
}