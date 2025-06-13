package com.example.targetlog.data.db.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.checkerframework.common.aliasing.qual.Unique
import java.io.Serializable
import java.util.Date
import java.util.UUID


@Entity(tableName = "session")
data class Session(
    @PrimaryKey
    val entryUuid: String = UUID.randomUUID().toString(), // Unique ID for sync
    val sessionId: Long,
    val speed: String?,
    val hand: String?,
    val timestamp: Date
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Session) return false

        return entryUuid == other.entryUuid &&
                sessionId == other.sessionId &&
                speed == other.speed &&
                hand == other.hand &&
                timestamp == other.timestamp
    }

    override fun hashCode(): Int {
        var result = entryUuid.hashCode()
        result = 31 * result + sessionId.hashCode()
        result = 31 * result + (speed?.hashCode() ?: 0)
        result = 31 * result + (hand?.hashCode() ?: 0)
        result = 31 * result + timestamp.hashCode()
        return result
    }
}