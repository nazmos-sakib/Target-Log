package com.example.targetlog.db.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.targetlog.data.db.room.Session
import com.example.targetlog.data.db.room.SessionIdCount
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Session): Long  //update + insert

    @Query("SELECT COUNT(*) FROM session")
    fun getTotalWorkoutCount(): Flow<Int>

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

    @Query("DELETE FROM session")
    suspend fun deleteAllData()
}