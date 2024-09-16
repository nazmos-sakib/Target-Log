package com.example.targetlog.main_activity.screens.common_components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.targetlog.R


@Composable
fun SettingsCard(
    resourcesId: Int,
    text:String,
    route:String? = null,
    onClickNavigate:(String)->Unit = {}
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { route?.let{onClickNavigate(it)}},
        horizontalArrangement  = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(id = resourcesId), contentDescription = "profile icon", tint = Color.White)
            Spacer(modifier = Modifier.width(15.dp))
            Text(text = text, color = Color.White)
        }
        Icon(painter = painterResource(id = R.drawable.right_arrow), contentDescription = "arrow",tint = Color.White)
    }
}


@Preview
@Composable
fun SettingsCardPreview(){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement  = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(id = R.drawable.profile_2), contentDescription = "profile icon", tint = Color.White)
            Spacer(modifier = Modifier.width(15.dp))
            Text(text = "Personal", color = Color.White)
        }
        Icon(painter = painterResource(id = R.drawable.right_arrow), contentDescription = "arrow",tint = Color.White)
    }
}