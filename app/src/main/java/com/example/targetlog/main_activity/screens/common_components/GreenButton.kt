package com.example.targetlog.main_activity.screens.common_components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.targetlog.ui.theme.GreenDark
import com.example.targetlog.ui.theme.GreenLight


@Composable
fun GreenButton(
    onButtonClick: ()->Unit,
    enabled: Boolean = true,
    content: @Composable() (RowScope.() -> Unit),
){
    Button(
        onClick = { onButtonClick() },
        modifier = Modifier
            .fillMaxWidth( ) ,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonColors(GreenLight, Color.Black, GreenDark, Color.Black),
        enabled  = enabled,
    ) {
        content()
    }
}

@Preview
@Composable
fun GreenButtonPreview(){
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth( )
            .border(4.dp, GreenLight, RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonColors(GreenLight, Color.Black, GreenLight, GreenLight)
    ) {
        Text(text = "Add All")
    }
}