package com.example.targetlog.training_activity.common_component

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.targetlog.main_activity.screens.common_components.GreenButton


//@Preview
@Composable
fun TrainingIsActive(
    onClick:()->Unit
){
    Box(modifier = Modifier, contentAlignment = Alignment.Center) {
        GreenButton(onButtonClick = onClick) {
            Text(text = "PAUSE", fontSize = 26.sp, fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview
@Composable
fun TrainingIsActivePreview(
){
    Box(modifier = Modifier, contentAlignment = Alignment.Center) {
        GreenButton(onButtonClick = { /*TODO*/ }) {
            Text(text = "PAUSE", fontSize = 26.sp, fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold)
        }
    }
}
