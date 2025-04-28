package com.example.targetlog.training_activity.shooting

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


 import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
 import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
 import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.targetlog.R

@Preview
@Composable
fun StartSessionPreview(){
    StartSession(
        modifier = Modifier.fillMaxWidth(1f)
    )
}


@Composable
fun StartSession(
    modifier: Modifier = Modifier,
    onSessionStart: () -> Unit = {}
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(.7f)
                .clickable {
                    onSessionStart()
                }
                .border(2.dp, color = Color.White, shape = RoundedCornerShape(10.dp))
                .padding(20.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.target_1212), contentDescription = null,
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp),
                tint = Color.White
            )
            Text(
                text = "Start a Session",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                softWrap = true
            )
        }
    }
}