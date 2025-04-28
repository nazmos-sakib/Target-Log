package com.example.targetlog.domain

typealias BluetoothDeviceDomain = BluetoothDevice

data class BluetoothDevice(
    val name:String?,
    val address:String,
    val type: Int,
    val bondState:Int
)