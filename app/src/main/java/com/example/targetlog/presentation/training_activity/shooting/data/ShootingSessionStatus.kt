package com.example.targetlog.presentation.training_activity.shooting.data

sealed interface ShootingSessionStatus {
    data object BluetoothConnected: ShootingSessionStatus
    data object SessionStarted: ShootingSessionStatus
    data object NotConnected: ShootingSessionStatus
}