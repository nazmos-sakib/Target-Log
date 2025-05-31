package com.example.targetlog.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.targetlog.data.db.Session
import com.example.android_esp32_presure_sensore_esp_now.data.db.SessionIdCount

@Dao
interface SessionDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Session): Long  //update + insert

    @Query("SELECT * FROM session WHERE sessionId = :sessionId")
    fun getSessionsById(sessionId: Long): LiveData<List<Session>>

    @Query("SELECT * FROM session ")
    fun getAllSessions(): LiveData<List<Session>>

    @Query(
        """
        SELECT sessionId, COUNT(sessionId) AS count 
        FROM session 
        GROUP BY sessionId
    """
    )
    fun getSessionsHistory(): List<SessionIdCount>

    @Query("SELECT * FROM session WHERE sessionId = :sessionId")
    fun getSessionDetailsBySessionId(sessionId: Long): List<Session>

    @Query(
        """
        SELECT * FROM session 
        WHERE sessionId = :sessionId 
        ORDER BY timestamp DESC 
        LIMIT :limit OFFSET :offset
        """
    )
    fun getSessionDetailsBySessionIdWithLimits(
        sessionId: Long,
        limit: Int,
        offset: Int
    ): List<Session>

    @Query("SELECT * FROM session ORDER BY timestamp DESC LIMIT :limit OFFSET :offset")
    suspend fun getSessionsPaged(limit: Int, offset: Int): List<Session>


    @Delete
    suspend fun deleteSession(article: Session)
}