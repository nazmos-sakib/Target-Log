package com.example.targetlog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.targetlog.commons.TRAINING_MODE_SHOOTING
import com.example.targetlog.training_activity.shooting.ShootingTrainingScreen
import com.example.targetlog.training_activity.wall_ball.TrainingWallBallScreen
import com.example.targetlog.ui.theme.TargetLogTheme

class TrainingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val trainingMode =  intent.getStringExtra("training_mode")

        setContent {
            TargetLogTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Box(modifier = Modifier.padding(innerPadding)) {
                        if(trainingMode.equals(TRAINING_MODE_SHOOTING)){
                            ShootingTrainingScreen()
                        } else {
                            TrainingWallBallScreen()
                        }
                    }
                }
            }
        }
    }

}

