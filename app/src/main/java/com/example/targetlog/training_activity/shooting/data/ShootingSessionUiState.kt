package com.example.targetlog.training_activity.shooting.data

import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import com.example.targetlog.domain.BluetoothMessage

@OptIn(ExperimentalMaterial3Api::class)
data class ShootingSessionUiState   constructor(
    val scaffoldState: BottomSheetScaffoldState? = null,
    val messages:List<BluetoothMessage> = emptyList(),
    val lastMessage:String? = "00",
    val isBluetoothConnected:Boolean = false,
    val isSessionStarted:Boolean = false,
    val isTrainingOnPause:Boolean = false,
    val errMessage:String? = null,
)
