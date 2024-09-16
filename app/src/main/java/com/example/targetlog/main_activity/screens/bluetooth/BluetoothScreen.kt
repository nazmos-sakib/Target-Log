package com.example.targetlog.main_activity.screens.bluetooth

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
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
import com.example.targetlog.ui.theme.GreenLight

@Preview
@Composable
fun BluetoothScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment  = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Image(
            painter = painterResource(id = R.drawable.bluetooth),
            contentDescription = "Bluetooth logo",
            modifier = Modifier.size(180.dp)
        )


        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "ENABLE",
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic
        )
        Text(
            text = "BLUETOOTH",
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Excited to use your shiny new R1 ball",
            color = Color.White,
            textAlign = TextAlign.Center,)


        Spacer(modifier = Modifier.height(15.dp))
        Text(
            modifier = Modifier.width(200.dp),
            text = "We Need to enable bluetooth on get this party started",
            color = Color.White,
            textAlign = TextAlign.Center,
            maxLines = 2
            )

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(5.dp),
            colors =  ButtonColors(GreenLight, Color.Black, Color.Black, Color.Black),
            modifier = Modifier
                .fillMaxWidth(.5f)
        ) {
            Text(text = "Allow Bluetooth")
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(5.dp),
            colors =  ButtonColors(Color.Black, Color.White, Color.Black, Color.Black),
            modifier = Modifier
                .fillMaxWidth(.5f)
                .border(1.dp,Color.White, RoundedCornerShape(5.dp))
        ) {
            Text(text = "Not Now")
        }
    }
}