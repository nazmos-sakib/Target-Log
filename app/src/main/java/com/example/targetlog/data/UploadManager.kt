package com.example.targetlog.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch


object UploadManager {
    private val uploadJob = SupervisorJob()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + uploadJob)

    fun uploadData(block: suspend CoroutineScope.() -> Unit) {
        coroutineScope.launch {
            block()
        }
    }

    fun <T> uploadData(data: T, block: suspend CoroutineScope.(T) -> Unit) {
        coroutineScope.launch {
            block(data)
        }

        /*--example--
        UploadManager.uploadData(data) {
            fireStoreService.uploadSessionToFirebase(
                data = it,
                onError = { Log.d(TAG, "listen: firestore upload error: $it") }
            )
        }*/
    }


    fun cancelAllUploads() {
        uploadJob.cancel()
    }
}
