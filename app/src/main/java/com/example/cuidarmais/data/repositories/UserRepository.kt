package com.example.cuidarmais.data.repositories

import com.example.cuidarmais.data.room.daos.UserDao
import com.example.cuidarmais.data.room.db.AppDatabase
import com.example.cuidarmais.data.room.models.User

class UserRepository(
    private val userDao: UserDao
) {

    suspend fun authenticate(email:String, password:String): User? {
        return userDao.authenticate(email, password)
    }

    suspend fun create(name: String, email: String, password: String): User {
        val foundUser = userDao.getUserByEmail(email)
        if (foundUser != null) {
            throw Error("Email already registered")
        }
        val user = User(name = name, email = email, password = password)
        userDao.insert(user)
        return userDao.getUserByEmail(email)!!
    }

    suspend fun update(user: User): User {
        userDao.update(user)
        return userDao.getUserByEmail(user.email)!!

    }
}