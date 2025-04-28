package com.example.targetlog.data

 import com.example.targetlog.domain.BluetoothMessage
 import java.util.Date

fun String.toBluetoothMessage(isFromLocalUser:Boolean,senderName:String): BluetoothMessage {

    return BluetoothMessage(
        message = this,
        timestamp =  Date()
    )
}

fun BluetoothMessage.toByteArray():ByteArray{
    return "$message\n".encodeToByteArray()
}