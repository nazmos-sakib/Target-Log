package com.example.targetlog.db.room.repository

import androidx.lifecycle.LiveData
import com.example.targetlog.data.db.room.SessionIdCount
import com.example.targetlog.data.db.room.Session
import com.example.targetlog.domain.BluetoothMessage
import kotlinx.coroutines.flow.Flow

interface SessionRepository {
    val getTotalWorkoutCountFlow: Flow<Int>
    suspend fun getTotalCount(): Int
    fun getAllSessions(): LiveData<List<Session>>
    fun getSessionsById(sessionId: Long): LiveData<List<Session>>

    suspend fun insertAll(sessionID: Long, messages: List<BluetoothMessage>)
    suspend fun insertAll(sessions: List<Session>)
    suspend fun firestoreToLocalInsertAll(sessions: List<Session>)

    suspend fun insert(session: Session): Long
    suspend fun insert(
        sessionID: Long, trainingHand: String?, message: BluetoothMessage,
        onFinishCallBack:(session:Session)->Unit
    )


    suspend fun deleteArticle(session: Session)
    suspend fun deleteAllData()

    suspend fun getSessionsHistory(): List<SessionIdCount>
    suspend fun getSessionDetailsBySessionId(sessionId: Long): List<Session>

    suspend fun getAllSessionsPaged(limit: Int, offset: Int): List<Session>
    suspend fun getSessionsBySessionIdPaged(
        sessionId: Long,
        limit: Int,
        offset: Int
    ): List<Session>
}