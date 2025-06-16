package com.example.targetlog.presentation.training_activity.shooting


import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.targetlog.MainActivity
import com.example.targetlog.R
import com.example.targetlog.domain.BluetoothMessage
import com.example.targetlog.presentation.training_activity.common_component.LeftRightDropdownMenu
import com.example.targetlog.presentation.ui.theme.DarkLight
import com.example.targetlog.presentation.ui.theme.Purple97
import com.example.targetlog.presentation.ui.theme.Yellow900
import kotlinx.coroutines.delay


@Preview
@Composable
fun SessionStartedScreenPreview(){

}

@SuppressLint("DefaultLocale")
@Composable
fun SessionStartedScreen(
    modifier: Modifier = Modifier,
    isTrainingOnPause: Boolean = false,
    lastMessage: String? = "",
    trainingHand: String,
    onTrainingHandChange: (String)->Unit = {},
    bottomSheetClickable: ()->Unit = {},
) {

    //calculate session time
    //stop/resume when training is pause
    var seconds by remember { mutableIntStateOf(-1) }
    LaunchedEffect(isTrainingOnPause) {
        while (!isTrainingOnPause) {
            delay(1000L)
            seconds++
        }
    }
    val hours = seconds / 3600
    val minutes = (seconds % 3600) / 60
    val remainingSeconds = seconds % 60
    //end

    //number of raps calculate
    var numberOfRaps by remember { mutableIntStateOf(0)}
    LaunchedEffect(lastMessage) {
        numberOfRaps ++
    }//end

    //average
    var total by remember { mutableIntStateOf(0) }
    var lastShoot by remember { mutableIntStateOf(0) }
    LaunchedEffect(key1 = lastMessage) {
        val values  = lastMessage?.split(",") // Split the string on the comma

        if (values != null) {
            if (values.size == 2) { // Ensure there are two values
                //clampedHead.intValue = values[0].toInt().coerceIn(0,255) // Parse the first value
                lastShoot = values[1].toInt().coerceIn(0, 255) // Parse the second value
                total += lastShoot
            } else {
                println("Received unexpected data: ")
            }
        }
    }




    Box(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Training Time",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds),
                color = Color.White,
                fontSize = 50.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(15.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(.7f)
                        .background(DarkLight, RoundedCornerShape(8.dp))
                        .padding(10.dp, 10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Throw Hand:", color = Color.White, modifier = Modifier)

                    //drop box
                    /*var trainingHand by remember {
                        mutableStateOf("Right")
                    }*/

                    Text(
                        text = trainingHand,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                    )
                    LeftRightDropdownMenu { onTrainingHandChange(it)  }
                }

                //bottom sheet layout show/hide
                Image(
                    modifier = Modifier.clickable { bottomSheetClickable() },
                    painter = painterResource(id = R.drawable.shoot_details),
                    contentDescription = ""
                )
            }
            Spacer(modifier = Modifier.height(15.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Number of Reps",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = String.format("%03d",numberOfRaps),
                    color = Purple97,
                    fontSize = 60.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                )

            }
            Spacer(modifier = Modifier.height(15.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Last Shot (mph)",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = String.format("%03d",lastShoot),
                    color = Yellow900,
                    fontSize = 60.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(15.dp))



            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Average Speed (mph)",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = String.format("%02d", when( numberOfRaps>0){
                        true-> total/numberOfRaps
                        false-> total
                    } ),
                    color = Purple97,
                    fontSize = 60.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                )
            }

        }

    }
}