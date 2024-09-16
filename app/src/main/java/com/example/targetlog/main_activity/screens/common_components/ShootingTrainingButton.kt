package com.example.targetlog.main_activity.screens.common_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.targetlog.R
import com.example.targetlog.ui.theme.Blue43FF
import com.example.targetlog.ui.theme.WhiteA9

@Preview
@Composable
fun ShootingTrainingButton(
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 10.dp, 0.dp, 0.dp,)
            .background(Blue43FF, RoundedCornerShape(8.dp)),
        horizontalArrangement  = Arrangement.Start,
        verticalAlignment  = Alignment.Top,
    ) {
        Column(
            modifier = Modifier. padding(30.dp,20.dp),
            verticalArrangement  = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "SHOOTING", color = Color.White, fontSize = 30.sp, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic)
            Text(text = "Record and track how fast you shoot with each shot!",modifier = Modifier.fillMaxWidth(.7f),color = WhiteA9, minLines = 2)
        }

    }
    Row(
        modifier = Modifier
            .fillMaxWidth() ,
        horizontalArrangement  = Arrangement.End,
        verticalAlignment  = Alignment.Top,
    ) {
        Image(painter = painterResource(id = R.drawable.shooting_blue_ball), contentDescription = "",modifier = Modifier.size(120.dp))
    }
}