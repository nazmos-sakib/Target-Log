package com.example.targetlog.main_activity.screens.find_my_target

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
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
import com.example.targetlog.main_activity.screens.common_components.TopBar
import com.example.targetlog.ui.theme.GreenLight

@Preview
@Composable
fun FindMyTargetScreen(
    onBackClickNavigate: () -> Unit = { },
) {
    Scaffold(
        topBar = {
            TopBar(
                title = "FIND MY BALL",
                onBluetoothButtonClick = {},
                backNavigate  = true,
                onBackClickNavigate = onBackClickNavigate,
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp, innerPadding.calculateTopPadding() + 20.dp, 20.dp, 20.dp),
            verticalArrangement  = Arrangement.Center,
            horizontalAlignment  = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "FIND MY BALL",
                modifier = Modifier.fillMaxWidth(.8f),
                textAlign = TextAlign.Center, color = Color.White,
                fontSize = 36.sp, fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                lineHeight = 40.sp
            )
            Text(
                text = "R1",
                modifier = Modifier
                    .fillMaxWidth(.8f)
                    .padding(10.dp),
                textAlign = TextAlign.Center, color = GreenLight,
                fontSize = 36.sp,
                lineHeight = 40.sp
            )
            Image(modifier = Modifier.padding(5.dp),painter = painterResource(id = R.drawable.bluetooth_signal_weak), contentDescription = null)
            Text(
                text = "Signal is strong",
                modifier = Modifier
                    .fillMaxWidth(.8f)
                    .padding(10.dp),
                textAlign = TextAlign.Center, color = Color.White,
                fontSize = 36.sp,
                lineHeight = 40.sp
            )
        }

    }
}