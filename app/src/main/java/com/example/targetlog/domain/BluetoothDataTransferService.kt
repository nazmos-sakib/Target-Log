package com.example.targetlog.domain

import kotlinx.coroutines.flow.Flow

interface BluetoothDataTransferService {
    fun listenForIncomingMessages(): Flow<BluetoothMessage>
    suspend fun sendMessage(bytes:ByteArray):Boolean
}