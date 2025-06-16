package com.example.targetlog.presentation.main_activity.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.targetlog.commons.ERROR_TAG
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class AppViewModel : ViewModel() {
    fun runInCoroutineBlock(block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->
                Log.d(ERROR_TAG, throwable.message.orEmpty())
            },
            block = block
        )

    fun runInCoroutineBlockWithContext(block: suspend CoroutineScope.() -> Unit): Job {
        val handler = CoroutineExceptionHandler { _, throwable ->
            Log.d(ERROR_TAG, throwable.message.orEmpty())
        }

        return viewModelScope.launch(Dispatchers.IO + handler) {
            block()
        }
    }
}