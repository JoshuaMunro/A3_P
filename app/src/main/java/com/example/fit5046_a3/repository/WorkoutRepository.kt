package com.example.fit5046_a3.repository

import android.app.Application
import com.example.fit5046_a3.dao.WorkoutDao
import com.example.fit5046_a3.database.UserRoomDatabase
import com.example.fit5046_a3.model.Workout
import kotlinx.coroutines.flow.Flow

class WorkoutRepository(application: Application) {
    private val workoutDao: WorkoutDao = UserRoomDatabase.getDatabase(application).workoutDao()

    fun getWorkoutsByUser(userId: Long): Flow<List<Workout>> {
        return workoutDao.getWorkoutsByUser(userId)
    }

    suspend fun insert(workout: Workout) {
        workoutDao.insertWorkout(workout)
    }

    suspend fun update(workout: Workout) {
        workoutDao.updateWorkout(workout)
    }

    suspend fun delete(workout: Workout) {
        workoutDao.deleteWorkout(workout)
    }
}