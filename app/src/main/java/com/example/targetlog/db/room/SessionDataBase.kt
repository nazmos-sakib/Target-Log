package com.example.targetlog.db.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.targetlog.commons.DB_NAME
import com.example.targetlog.data.db.room.Session

@Database(
    entities = [Session::class],
    version = 2
)
@TypeConverters(Converters::class)
abstract class SessionDataBase:RoomDatabase(){
    abstract fun getSessionDao(): SessionDAO
    companion object{
        @Volatile  //other thread can see when one thread change the instances
        private var instance: SessionDataBase? = null
        private val  LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also{ instance =it}
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context,
            SessionDataBase::class.java,
            DB_NAME,
        ).build()
    }
}