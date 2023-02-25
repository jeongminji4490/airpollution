package com.example.airpollution.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

@androidx.room.Database(
    entities = [AirPollution::class],
    version = 1,
    exportSchema = false
)
abstract class Database : RoomDatabase() {
    abstract val dao: Dao

    companion object {
        @Volatile
        private var INSTANCE : Database? = null

        fun getInstance(context: Context) : Database {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        Database::class.java,
                        "MAIN_DB"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}