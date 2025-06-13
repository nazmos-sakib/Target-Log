package com.example.targetlog.module

import android.content.Context
import androidx.room.Room
//import androidx.room.Room
import com.example.targetlog.commons.DB_NAME
import com.example.targetlog.data.AndroidBluetoothController
import com.example.targetlog.data.db.room.migrations.MIGRATION_1_2
import com.example.targetlog.data.db.room.repository.SessionRepositoryImpl
import com.example.targetlog.db.room.SessionDataBase
import com.example.targetlog.db.room.repository.SessionRepository
import com.example.targetlog.domain.BluetoothController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideBluetoothController(@ApplicationContext context: Context): BluetoothController {
        return AndroidBluetoothController(context)
    }
    /*
        @Provides
        @Singleton
        fun provideBluetoothControllerForESP(@ApplicationContext context: Context): BluetoothControllerForESP {
            return AndroidBluetoothControllerForESP(context)
        }
*/
        @Provides
        @Singleton
        fun provideSessionDatabase(@ApplicationContext context: Context): SessionDataBase {
            return  Room.databaseBuilder(
                context,
                SessionDataBase::class.java,
                DB_NAME,
            )
                .addMigrations(MIGRATION_1_2)  //session schema changed. migration needed
                .build()
        }

        @Provides
        @Singleton
        fun providesSessionRepository(db: SessionDataBase): SessionRepository {
            return SessionRepositoryImpl(db)
        }
}