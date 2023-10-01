package com.example.filmshakerkotlin.data.local.watched

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WatchedEntity::class], version = 1)
abstract class WatchedDatabase : RoomDatabase() {
    abstract fun watchedDao(): WatchedDao

    companion object {
        @Volatile
        private var instance: WatchedDatabase? = null

        fun getInstance(context: Context): WatchedDatabase {
            if (instance == null) {
                synchronized(WatchedDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        WatchedDatabase::class.java,
                        "watched_database"
                    ).build()
                }
            }
            return instance!!
        }
    }

}