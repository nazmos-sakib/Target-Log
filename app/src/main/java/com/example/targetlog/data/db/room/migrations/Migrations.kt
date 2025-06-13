package com.example.targetlog.data.db.room.migrations

// File: data/db/Migrations.kt

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.UUID

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        // 1. Create a new table with the updated schema
        db.execSQL("""
            CREATE TABLE session_new (
                entryUuid TEXT NOT NULL PRIMARY KEY,
                sessionId INTEGER NOT NULL,
                speed TEXT,
                hand TEXT,
                timestamp INTEGER NOT NULL
            )
        """.trimIndent())

        // 2. Copy the old data into the new table
        val cursor = db.query("SELECT * FROM session")
        while (cursor.moveToNext()) {
            val sessionId = cursor.getLong(cursor.getColumnIndexOrThrow("sessionId"))
            val speed = cursor.getString(cursor.getColumnIndexOrThrow("speed"))
            val hand = cursor.getString(cursor.getColumnIndexOrThrow("hand"))
            val timestamp = cursor.getLong(cursor.getColumnIndexOrThrow("timestamp"))

            val entryUuid = UUID.randomUUID().toString()

            db.execSQL("""
                INSERT INTO session_new (entryUuid, sessionId, speed, hand, timestamp)
                VALUES (?, ?, ?, ?, ?)
            """, arrayOf(entryUuid, sessionId, speed, hand, timestamp))
        }
        cursor.close()

        // 3. Drop old table and rename new one
        db.execSQL("DROP TABLE session")
        db.execSQL("ALTER TABLE session_new RENAME TO session")
    }
}
