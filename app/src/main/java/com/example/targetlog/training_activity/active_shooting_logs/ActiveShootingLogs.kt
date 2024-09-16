package com.example.targetlog.training_activity.active_shooting_logs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.targetlog.R
import com.example.targetlog.ui.theme.GreenLight

@Preview
@Composable
fun ActiveShootingLogs(){
    Scaffold(
        modifier = Modifier.padding(0.dp,0.dp),
        topBar = { LogsTopBar()},
        containerColor = Color.Black
    ) {innerPadding->
        Box(
            modifier = Modifier
                .padding(20.dp,innerPadding.calculateTopPadding(),20.dp,20.dp)
                .fillMaxSize()
        ) {

            LazyColumn {
                items(40){
                    ActiveShootingLogCard()
                }
            }
        }
    }
}

@Preview
@Composable
fun LogsTopBar(){
    Row(
        modifier  = Modifier.fillMaxWidth().padding(20.dp,0.dp),
        horizontalArrangement  = Arrangement.SpaceBetween,
        verticalAlignment  = Alignment.CenterVertically,
    ) {
        Button(onClick = { /*TODO*/ },contentPadding= PaddingValues(0.dp), colors = ButtonColors(Color.Transparent,Color.White,Color.Transparent,Color.Transparent)) {
            Icon(painter = painterResource(id = R.drawable.back_arrow_24), contentDescription = "")
            Text(text = "BACK")
        }
        Text(text = "MY SHOTS", color = GreenLight, fontSize = 20.sp, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic)
    }
}

@Preview
@Composable
fun ActiveShootingLogCard(){
    Row(
        modifier  = Modifier.fillMaxWidth().padding(0.dp,10.dp),
        horizontalArrangement  = Arrangement.SpaceBetween,
        verticalAlignment  = Alignment.CenterVertically,
    )  {

        Row(
            modifier  = Modifier.fillMaxWidth(.35f),
            horizontalArrangement  = Arrangement.SpaceBetween,
            verticalAlignment  = Alignment.CenterVertically,
        )  {
            Text(text = "57", color = GreenLight, fontSize = 20.sp)
            Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.White,fontSize = 20.sp)) {
                    append("62.2")
                }
                append(" ")

                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.White)) {
                    append("mph")
                }
            })

        }
        Row(
            modifier  = Modifier.fillMaxWidth(.6f),
            horizontalArrangement  = Arrangement.SpaceBetween,
            verticalAlignment  = Alignment.CenterVertically,
        )  {
            Text(text = "00:24:47", color = GreenLight, fontSize = 20.sp)
            Box(modifier = Modifier.border(1.dp,color = Color.White, shape = CircleShape).padding(0.dp)) {
                 Icon(modifier = Modifier.size(18.dp).padding(3.dp),painter = painterResource(id = R.drawable.baseline_close_24), contentDescription = "", tint = Color.White )
            }
        }
    }
}