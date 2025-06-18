package com.example.targetlog.data.db.firebase

data class FirestoreSessionList(
    val userId: String = "",
    val listOfSession: List<FirestoreSession> = emptyList()
)
