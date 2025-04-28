package com.example.targetlog.data

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import com.example.targetlog.domain.BluetoothDeviceDomain

@SuppressLint("MissingPermission")
fun BluetoothDevice.toBluetoothDeviceDomain():BluetoothDeviceDomain{
    return BluetoothDeviceDomain(
        name = name,
        address = address,
        type = type,
        bondState = bondState
    )
}