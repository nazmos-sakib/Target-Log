package com.example.targetlog.training_activity.shooting

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.targetlog.MainActivity

@Preview
@Composable
fun NotConnectedScreen(){
    Column (
        verticalArrangement  = Arrangement.Center,
    horizontalAlignment  = Alignment.CenterHorizontally,
    ){
        val context = LocalContext.current
        Text(text = "you re not connected to the bluetooth device")
        Button(onClick = {
            Intent(context, MainActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                context.startActivity(it)
            }
        }) {
            Text(text = "Go Back")
        }
    }
}