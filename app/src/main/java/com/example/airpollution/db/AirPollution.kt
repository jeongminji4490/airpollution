package com.example.airpollution.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "airpollutions")
data class AirPollution(
    @PrimaryKey(autoGenerate = true)
    val serialNum: Int,
    val districtName : String,
    val moveName : String,
    val issueDate : String,
    val issueTime : String,
    val issueGbn : String,
    val itemCode : String,
    val notification : Int
)
