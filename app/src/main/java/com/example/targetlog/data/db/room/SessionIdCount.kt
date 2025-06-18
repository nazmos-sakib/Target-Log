package com.example.targetlog.data.db.room

import java.util.Date

data class SessionIdCount(
    val sessionId: Long,
    val count: Int,
    val date: Date
)
