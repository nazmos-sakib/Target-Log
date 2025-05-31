package com.example.targetlog

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.targetlog.commons.BOTTOM_NAV_TRAINING_SCREEN
import com.example.targetlog.commons.SESSION_ID
import com.example.targetlog.commons.SPLASH_SCREEN
import com.example.targetlog.commons.WORKOUT_HISTORY_SCREEN
import com.example.targetlog.commons.WORKOUT_HISTORY_START_DESTINATION_SCREEN
import com.example.targetlog.data.persistent.SessionManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //val intentResult1 = intent.getBooleanExtra("training_finish",false)
        val route = determineStartDestination(intent)
        setContent {
            AppEntry(startDestination = route)
        }
    }

    private fun determineStartDestination(intent: Intent?): String {
        // Check if the intent has the "training_finish" extra
        if (intent?.getBooleanExtra("training_finish", false) == true) {
            val sessionId = intent.getLongExtra("session_id", -1L)
            // Return the route with arguments if needed
            Log.d("MainActivity", "determineStartDestination: $sessionId")
            if (sessionId != -1L) {
                SessionManager.sessionId = sessionId.toString()
                //return "$WORKOUT_HISTORY?sessionId=$sessionId"
                //return "$WORKOUT_HISTORY_SCREEN?$SESSION_ID=${sessionId}"
                return WORKOUT_HISTORY_START_DESTINATION_SCREEN
            }
            //return "$WORKOUT_HISTORY?sessionId=$sessionId"
            //return "$WORKOUT_HISTORY/{$sessionId}"
        }
        return SPLASH_SCREEN  // usual start destination
    }
}
