package com.example.targetlog.data.db.firebase

data class FirestoreSession(
    val entryUuid: String = "",
    val sessionId: Long = 0L,
    val speed: String? = null,
    val hand: String? = null,
    val timestamp: Long = 0L
)



