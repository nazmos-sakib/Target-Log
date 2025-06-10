package com.example.targetlog.db.repository

import androidx.lifecycle.LiveData
import com.example.targetlog.data.db.SessionIdCount
import com.example.targetlog.data.db.Session
import com.example.targetlog.domain.BluetoothMessage
import kotlinx.coroutines.flow.Flow

interface SessionRepository {
    //suspend fun getTotalSessionCount(): Flow<Int>
    val getTotalWorkoutCountFlow: Flow<Int>
    fun getAllSessions(): LiveData<List<Session>>
    fun getSessionsById(sessionId: Long): LiveData<List<Session>>

    suspend fun insertAll(sessionID: Long, messages: List<BluetoothMessage>)
    suspend fun insertAll(sessions: List<Session>)

    suspend fun insert(session: Session): Long
    suspend fun insert(sessionID: Long, trainingHand: String?, message: BluetoothMessage)


    suspend fun deleteArticle(session: Session)

    suspend fun getSessionsHistory(): List<SessionIdCount>
    suspend fun getSessionDetailsBySessionId(sessionId: Long): List<Session>

    suspend fun getAllSessionsPaged(limit: Int, offset: Int): List<Session>
    suspend fun getSessionsBySessionIdPaged(
        sessionId: Long,
        limit: Int,
        offset: Int
    ): List<Session>
}