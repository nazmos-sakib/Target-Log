package com.example.targetlog.main_activity.screens.common_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.targetlog.R
import com.example.targetlog.ui.theme.Black4B
import com.example.targetlog.ui.theme.GreenLight

@Preview
@Composable
fun WallBallTrainingButton(
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 10.dp, 0.dp, 0.dp)
            .background(GreenLight, RoundedCornerShape(8.dp)),
        horizontalArrangement  = Arrangement.End,
        verticalAlignment  = Alignment.Top,
    ) {
        Column(
            modifier = Modifier .padding(30.dp,20.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(text = "WALL BALL",  color = Color.Black, fontSize = 30.sp,textAlign = TextAlign.End, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic)
            Text(text = "Track and time your wall ball repetitions.",modifier = Modifier.fillMaxWidth(.5f),color = Black4B, textAlign = TextAlign.End, minLines = 2)
        }

    }
    Box(
        modifier = Modifier
            .fillMaxWidth() ,
        contentAlignment = Alignment.TopStart
    ) {
            Image(painter = painterResource(id = R.drawable.wall_ball_bg), contentDescription = "", modifier = Modifier.size(130.dp)   )
            Image(painter = painterResource(id = R.drawable.wall_ball_fg), contentDescription = "", modifier = Modifier.size(130.dp).padding(8.dp)  )

    }
}

@Preview
@Composable
fun WallBallTrainingButtonPreview(
){

    Column(
        modifier = Modifier.fillMaxWidth().padding(0.dp,20.dp,0.dp,0.dp).background(GreenLight, RoundedCornerShape(8.dp)) .padding(30.dp,20.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement  = Arrangement.SpaceEvenly,
    ) {
        Text(text = "WALL BALL",  color = Color.Black, fontSize = 30.sp,textAlign = TextAlign.End, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic)
        Text(text = "Track and time your wall ball repetitions.",modifier = Modifier.fillMaxWidth(.5f),color = Black4B, textAlign = TextAlign.End, minLines = 2)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth() ,
        contentAlignment = Alignment.TopStart
    ) {
            Image(painter = painterResource(id = R.drawable.wall_ball_bg), contentDescription = "", modifier = Modifier.size(130.dp)   )
            Image(painter = painterResource(id = R.drawable.wall_ball_fg), contentDescription = "", modifier = Modifier.size(130.dp).padding(0.dp,8.dp)  )

    }
}