package com.example.targetlog.main_activity.screens.bottom_nav_training

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.targetlog.TrainingActivity
import com.example.targetlog.commons.TRAINING_MODE_SHOOTING
import com.example.targetlog.commons.TRAINING_MODE_WALL_BALL
import com.example.targetlog.main_activity.screens.common_components.ShootingTrainingButton
import com.example.targetlog.main_activity.screens.common_components.TopBar
import com.example.targetlog.main_activity.screens.common_components.WallBallTrainingButtonPreview

@Preview
@Composable
fun BottomNavTrainingScreen(
    onClickGotoBluetoothScreen:(String)->Unit={ _ -> }
){
    val context = LocalContext.current
    Scaffold(
        topBar= { TopBar(title = "TRAINING",onBluetoothButtonClick = onClickGotoBluetoothScreen) }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp, innerPadding.calculateTopPadding(), 20.dp, 0.dp),
            verticalArrangement  = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "CHOOSE YOUR TRAINING MODE",
                modifier = Modifier.fillMaxWidth(.8f),
                textAlign = TextAlign.Center,color = Color.White,
                fontSize = 36.sp, fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                lineHeight =  40.sp)
            Spacer(modifier = Modifier.height(30.dp))
            Box(
                modifier = Modifier
                    .clickable {
                        Intent(context,TrainingActivity::class.java).also {
                            it.putExtra("training_mode", TRAINING_MODE_SHOOTING)
                            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            context.startActivity(it)
                        }

                    }
            ) {
                ShootingTrainingButton()
            }
            Spacer(modifier = Modifier.height(30.dp))
            Box(
                modifier = Modifier
                    .clickable {
                        Intent(context,TrainingActivity::class.java).also {
                            it.putExtra("training_mode", TRAINING_MODE_WALL_BALL)
                            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            context.startActivity(it)
                        }
                    }
            )  {
                WallBallTrainingButtonPreview()
            }

        }
    }
}