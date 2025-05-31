package com.example.targetlog.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.Date


@Entity(tableName = "session")
data class Session(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var sessionId: Long,
    val speed: String?,
    val hand: String?,
    val timestamp: Date
):Serializable

{
    override fun hashCode(): Int {
        var result = id.hashCode()

        result = 31 * result + sessionId.hashCode()


        if(speed.isNullOrEmpty()){
            result = 31 * result + speed.hashCode()
        }
        if(hand.isNullOrEmpty()){
            result = 31 * result + hand.hashCode()
        }
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Session

        if (id != other.id) return false
        if (sessionId != other.sessionId) return false
        if (speed != other.speed) return false
        if (hand != other.hand) return false

        return true
    }
}