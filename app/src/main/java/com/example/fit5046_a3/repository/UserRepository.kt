package com.example.fit5046_a3.repository

import android.app.Application
import com.example.fit5046_a3.dao.UserDao
import com.example.fit5046_a3.database.UserRoomDatabase
import com.example.fit5046_a3.model.User
import kotlinx.coroutines.flow.Flow

class UserRepository (application: Application) {

    private var userDao: UserDao =
        UserRoomDatabase.getDatabase(application).userDao()

    val allUsers: Flow<List<User>> = userDao.getAllUsers()


    suspend fun insert(user: User) {
        userDao.insertUser(user)
    }

    suspend fun delete(user: User) {
        userDao.deleteUser(user)
    }

    suspend fun update(user: User) {
        userDao.updateUser(user)
    }

    fun getUserByEmail(email: String): Flow<User?> {
        return userDao.getUserByEmail(email)
    }
}