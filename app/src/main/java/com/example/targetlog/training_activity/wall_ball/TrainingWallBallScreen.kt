package com.example.targetlog.training_activity.wall_ball


import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.targetlog.MainActivity
import com.example.targetlog.R
import com.example.targetlog.main_activity.screens.common_components.CustomDialogBox
import com.example.targetlog.main_activity.screens.common_components.WallBallTrainingButtonPreview
import com.example.targetlog.training_activity.common_component.TrainingIsActive
import com.example.targetlog.training_activity.common_component.TrainingIsPaused
import com.example.targetlog.ui.theme.Purple97

@Preview
@Composable
fun TrainingWallBallScreen(){
    val context = LocalContext.current
    // dialog
    var isDialogShown by remember {
        mutableStateOf(false)
    }
    if (isDialogShown){
        Dialog(onDismissRequest = { isDialogShown = false }) {
            CustomDialogBox(
                rId = R.drawable.timer,
                text = "Are you sure you'd like to end this session?",
                greenText = "End Session",
                blueText = "Continue Session",
                onNegativeButtonClick = {isDialogShown = false},
                onPositiveButtonClick = {
                    Intent(context, MainActivity::class.java).also {
                        it.putExtra("training_finish", true)
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        context.startActivity(it)
                    }
                }
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box {
            WallBallTrainingButtonPreview()
        }
        Box {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement  = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = "Training Time", color = Color.White,fontSize = 20.sp,  fontWeight = FontWeight.Bold)
                Text(text = "00:18:32", color = Color.White,fontSize = 50.sp, fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(30.dp))
                Text(text = "Number of Reps", color = Color.White,fontSize = 20.sp,   fontWeight = FontWeight.Bold)
                Text(text = "32", color = Purple97,fontSize = 90.sp, fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold)

            }

        }
        var isTrainingActive by remember {
            mutableStateOf(true)
        }

        if (isTrainingActive) {
            TrainingIsActive { isTrainingActive = !isTrainingActive }
        } else {
            TrainingIsPaused(
                onStartClick = { isTrainingActive = !isTrainingActive },
                onEndClick = { isDialogShown = true}
            )
        }
    }

}
