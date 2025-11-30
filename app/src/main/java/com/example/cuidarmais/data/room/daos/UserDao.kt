package com.example.cuidarmais.data.room.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.cuidarmais.data.room.models.User

@Dao
interface UserDao {

    @Insert
    suspend fun insert ( user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    suspend fun authenticate(email: String, password: String): User?

    @Query("SELECT * FROM user where email = :email")
    suspend fun getUserByEmail(email: String): User?
}