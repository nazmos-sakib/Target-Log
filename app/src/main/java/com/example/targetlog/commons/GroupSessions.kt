package com.example.targetlog.commons

import com.example.targetlog.data.db.room.Session
import com.example.targetlog.data.db.room.SessionGroup
import com.example.targetlog.data.db.room.SessionIdCount
import java.text.SimpleDateFormat
import java.util.Locale

fun groupSessionsByMonth(sessions: List<Session>): List<SessionGroup> {
    val formatter = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
    return sessions.groupBy { session ->
        formatter.format(session.timestamp)
    }.map { (monthYear, sessionList) ->
        SessionGroup(monthYear, sessionList)
    }
}

fun List<Session>.toSessionIdCountList(): List<SessionIdCount> {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd",Locale.getDefault()) // Strips time
    return this
        .groupBy { Pair(it.sessionId, dateFormat.parse(dateFormat.format(it.timestamp))) }
        .map { (key, group) ->
            SessionIdCount(
                sessionId = key.first,
                count = group.size,
                date = key.second!!
            )
        }
}
