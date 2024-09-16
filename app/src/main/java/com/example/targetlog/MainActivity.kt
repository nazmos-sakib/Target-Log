package com.example.targetlog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.targetlog.commons.BOTTOM_NAV_TRAINING_SCREEN
import com.example.targetlog.commons.SPLASH_SCREEN
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val intentResult = intent.getBooleanExtra("training_finish",false)
        setContent {
            AppEntry(startDestination = when(intentResult) {
                true -> BOTTOM_NAV_TRAINING_SCREEN
                false -> SPLASH_SCREEN
            })
        }
    }
}
