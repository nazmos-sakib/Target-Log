package com.example.targetlog.training_activity.shooting

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.targetlog.MainActivity
import com.example.targetlog.R
import com.example.targetlog.main_activity.screens.common_components.CustomDialogBox
import com.example.targetlog.main_activity.screens.common_components.ShootingTrainingButton
import com.example.targetlog.moke_data.FakeViewModel
import com.example.targetlog.training_activity.active_shooting_logs.ActiveShootingLogs
import com.example.targetlog.training_activity.common_component.TrainingIsActive
import com.example.targetlog.training_activity.common_component.TrainingIsPaused
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@Preview
@Composable
fun ShootingTrainingScreenPreview() {
    ShootingTrainingScreen(
        //viewModel =  FakeViewModel(true)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
//@Preview
@Composable
fun ShootingTrainingScreen(
    viewModel: ShootingTrainingViewModel = hiltViewModel(),
    //viewModel: FakeViewModel = FakeViewModel(true) //TODO
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()

    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    // dialog
    var isDialogShown by remember {
        mutableStateOf(false)
    }
    if (isDialogShown) {
        Dialog(onDismissRequest = { isDialogShown = false }) {
            CustomDialogBox(
                rId = R.drawable.timer,
                text = "Are you sure you'd like to end this session?",
                greenText = "End Session",
                blueText = "Continue Session",
                onNegativeButtonClick = { isDialogShown = false },
                onPositiveButtonClick = {
                    viewModel.closeActiveSessionDataCollectionJob()
                    Log.d("Training Screen", "session ends with ID:${state.sessionId} ")
                    Intent(context, MainActivity::class.java).also {
                        it.putExtra("training_finish", true)
                        it.putExtra("session_id", state.sessionId)
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        context.startActivity(it)
                    }
                }
            )
        }
    }


        BottomSheetScaffold(
            scaffoldState = scaffoldState,
            sheetPeekHeight = 0.dp,
            sheetContent = {
                ActiveShootingLogs(
                    state.messages
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box {
                    ShootingTrainingButton()
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    /*var isSessionStarted by remember {
                        mutableStateOf(false)
                    }
                    var isSessionOnPause by remember {
                        mutableStateOf(false)
                    }*/
                    //when (state.isBluetoothConnected) {
                    when (state.isBluetoothConnected) {
                        true -> {

                            when (state.isSessionStarted) {
                                false -> {
                                    StartSession {
                                        //start session and wait for incoming data
                                        viewModel.startSession() //also call a member function "startAWorkoutSession()"
                                    }
                                }

                                true -> { //training started
                                    SessionStartedScreen(
                                        isTrainingOnPause = state.isTrainingOnPause,
                                        lastMessage = state.lastMessage,
                                        trainingHand = state.trainingHand,
                                        onTrainingHandChange =  viewModel::updateTrainingHand ,
                                        bottomSheetClickable = { scope.launch { scaffoldState.bottomSheetState.expand() } }
                                    )



                                    Spacer(modifier = Modifier.height(80.dp))

                                    if (! state.isTrainingOnPause) {
                                        //pause button
                                        TrainingIsActive {
                                            //onClick pause training
                                            //state.isSessionOnPause = !state.isSessionOnPause
                                            viewModel.pauseTraining()
                                        }
                                    } else {
                                        TrainingIsPaused(
                                            onStartClick = {
                                                //state.isSessionOnPause = !state.isSessionOnPause
                                                viewModel.startTraining()
                                            },
                                            onEndClick = {
                                                //viewModel.endSession()
                                                isDialogShown = true
                                            }
                                        )
                                    }
                                }
                            }
                        }

                        false -> {
                            NotConnectedScreen()
                        }
                    }
                }

            }


        }


}