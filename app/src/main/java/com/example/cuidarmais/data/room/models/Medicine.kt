package com.example.cuidarmais.data.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.DayOfWeek
import java.time.LocalTime

@Entity(tableName = "medicine")
data class Medicine (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "user_id") val userId: Int,
    val name: String,
    @ColumnInfo(name = "days_of_week") val daysOfWeek: List<DayOfWeek>,
    val times: List<LocalTime>,
    val dosage: String? = null,
    val notes: String? = null
)