package com.example.cuidarmais.data.room.db

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.DayOfWeek
import java.time.LocalTime

class Converters {
    private val gson = Gson()

    // --- Conversor para List<DayOfWeek> ---
    @TypeConverter
    fun fromDayOfWeekList(value: List<DayOfWeek>?): String? {
        return value?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toDayOfWeekList(value: String?): List<DayOfWeek>? {
        return value?.let {
            val listType = object : TypeToken<List<DayOfWeek>>() {}.type
            gson.fromJson(it, listType)
        }
    }

    // --- Conversor para List<LocalTime> (times) ---
    // LocalTime não converte bem nativamente com Gson simples,
    // então convertemos para List<String> primeiro.
    @TypeConverter
    fun fromLocalTimeList(value: List<LocalTime>?): String? {
        if (value == null) return null
        // Converte cada LocalTime para String (HH:mm) antes de salvar
        val stringList = value.map { it.toString() }
        return gson.toJson(stringList)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toLocalTimeList(value: String?): List<LocalTime>? {
        if (value == null) return null
        val listType = object : TypeToken<List<String>>() {}.type
        val stringList: List<String> = gson.fromJson(value, listType)

        // Reconverte as Strings de volta para LocalTime
        return stringList.map { it -> LocalTime.parse(it) }
    }
}