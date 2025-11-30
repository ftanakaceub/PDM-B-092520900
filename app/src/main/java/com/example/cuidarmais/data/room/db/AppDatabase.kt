package com.example.cuidarmais.data.room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cuidarmais.data.room.daos.MedicineDao
import com.example.cuidarmais.data.room.daos.UserDao
import com.example.cuidarmais.data.room.models.Medicine
import com.example.cuidarmais.data.room.models.User

@Database(
    entities = [
        User::class,
        Medicine::class
    ], version = 1
    , exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao
    abstract fun getMedicineDao(): MedicineDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                var instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "AppDatabase.sqlite"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}