package com.example.airpollution.db

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@androidx.room.Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: AirPollution)

    @Query("SELECT * FROM airpollutions WHERE notification = 0")
    fun selectAll() : List<AirPollution>

    @Query("DELETE FROM airpollutions")
    fun delete()
}