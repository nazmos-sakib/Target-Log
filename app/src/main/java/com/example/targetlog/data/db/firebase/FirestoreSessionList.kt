package com.example.targetlog.data.db.firebase

data class FirestoreSessionList(
    val userId: String = "",
    val sessions: List<FirestoreSession> = emptyList()
)
