package com.example.targetlog.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface BluetoothController {
    val isConnected: StateFlow<Boolean>
    val scannedDevices: StateFlow<List<BluetoothDevice>>
    val pairedDevices: StateFlow<List<BluetoothDevice>>
    val errors: SharedFlow<String>

    fun startDiscovery()
    fun stopDiscovery()

    fun updatePairedDevises()

    fun connectToESP(device:BluetoothDeviceDomain,callback: ()->Unit)
    fun startAWorkoutSession(): Flow<ConnectionResult>

    fun closeAllConnection()
    fun closeConnection()

    fun releaseAll()
    fun releaseBroadCastReceivers()

}