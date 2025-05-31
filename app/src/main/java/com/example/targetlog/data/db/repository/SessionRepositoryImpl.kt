package com.example.targetlog.data.db.repository

import com.example.android_esp32_presure_sensore_esp_now.data.db.SessionIdCount
import com.example.targetlog.data.db.Session
import com.example.targetlog.db.SessionDataBase
import com.example.targetlog.db.repository.SessionRepository
import com.example.targetlog.domain.BluetoothMessage
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor (
    private val db: SessionDataBase
) : SessionRepository {
    override fun getAllSessions() = db.getSessionDao().getAllSessions()
    override fun getSessionsById(sessionId: Long) = db.getSessionDao().getSessionsById(sessionId)

    override suspend fun insert(session: Session) = db.getSessionDao().insert(session)

    override suspend fun insertAll(sessionID: Long, messages: List<BluetoothMessage>) {
        messages.forEach { item ->
            val values  =  item.message.split(",") // Split the string on the comma
            if (values.size == 2) {
                insert(
                    Session(
                        sessionId = sessionID,
                        hand = values[0] ,
                        speed = values[1],
                        timestamp = item.timestamp
                    )
                )
            }
        }

    }

    override suspend fun insert(sessionID: Long, trainingHand:String?, message: BluetoothMessage) {

            val values  =   message.message.split(",") // Split the string on the comma
            if (values.size == 2) {
                insert(
                    Session(
                        sessionId = sessionID,
                        hand = trainingHand ,
                        speed = values[1],
                        timestamp = message.timestamp
                    )
                )
            }


    }

    override suspend fun insertAll( sessions: List<Session>) {
        sessions.forEach { session ->
            insert( session)
        }
    }

    override suspend fun deleteArticle(session: Session) = db.getSessionDao().deleteSession(session)

    override suspend fun getSessionsHistory(): List<SessionIdCount> = db.getSessionDao().getSessionsHistory()
    override suspend fun getAllSessionsPaged(limit: Int, offset: Int): List<Session> = db.getSessionDao().getSessionsPaged(limit, offset)

    override suspend fun getSessionDetailsBySessionId(sessionId: Long):List<Session> = db.getSessionDao().getSessionDetailsBySessionId(sessionId)
    override suspend fun getSessionsBySessionIdPaged(
        sessionId: Long,
        limit: Int,
        offset: Int
    ): List<Session> = db.getSessionDao().getSessionDetailsBySessionIdWithLimits(sessionId,limit, offset)


}