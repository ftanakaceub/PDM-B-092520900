package com.example.cuidarmais.data.room.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.cuidarmais.data.room.models.Medicine

@Dao
interface MedicineDao {

    @Insert
    suspend fun insert(medicine: Medicine)

    @Update
    suspend fun update(medicine: Medicine)

    @Delete
    suspend fun delete(medicine: Medicine)

    @Query("SELECT * FROM medicine WHERE user_id = :userId")
    suspend fun getMedicineList(userId: Int): List<Medicine>
}