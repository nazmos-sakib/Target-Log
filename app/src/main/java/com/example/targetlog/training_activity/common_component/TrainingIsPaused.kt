package com.example.targetlog.training_activity.common_component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.targetlog.ui.theme.Blue43FF
import com.example.targetlog.ui.theme.GreenLight


//@Preview
@Composable
fun TrainingIsPaused(
    onStartClick:()->Unit,
    onEndClick:  ()->Unit
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = onStartClick,
            modifier = Modifier
                .weight(1f)
                .border(4.dp, GreenLight, RoundedCornerShape(8.dp)),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonColors(GreenLight, Color.Black, GreenLight, GreenLight)
        ) {
            Text(text = "START", fontSize = 26.sp, fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.weight(.2f))
        Button(
            onClick = onEndClick,
            modifier = Modifier
                .weight(1f)
                .border(4.dp, Blue43FF, RoundedCornerShape(8.dp)),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonColors(Blue43FF, Color.White, GreenLight, GreenLight)
        ) {
            Text(text = "END", fontSize = 26.sp, fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview
@Composable
fun TrainingIsPausedPreview(
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .weight(1f)
                .border(4.dp, GreenLight, RoundedCornerShape(8.dp)),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonColors(GreenLight, Color.Black, GreenLight, GreenLight)
        ) {
            Text(text = "START", fontSize = 26.sp, fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.weight(.2f))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .weight(1f)
                .border(4.dp, Blue43FF, RoundedCornerShape(8.dp)),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonColors(Blue43FF, Color.White, GreenLight, GreenLight)
        ) {
            Text(text = "END", fontSize = 26.sp, fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold)
        }
    }
}