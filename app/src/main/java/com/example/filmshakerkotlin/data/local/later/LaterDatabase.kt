package com.example.filmshakerkotlin.data.local.later

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.filmshakerkotlin.data.local.watched.WatchedDao
import com.example.filmshakerkotlin.data.local.watched.WatchedDatabase
import com.example.filmshakerkotlin.data.local.watched.WatchedEntity

@Database(entities = [LaterEntity::class], version = 1)
abstract class LaterDatabase : RoomDatabase() {
    abstract fun laterDao(): LaterDao

    companion object {
        @Volatile
        private var instance: LaterDatabase? = null

        fun getInstance(context: Context): LaterDatabase {
            if (instance == null) {
                synchronized(LaterDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LaterDatabase::class.java,
                        "later_database"
                    ).build()
                }
            }
            return instance!!
        }
    }

}