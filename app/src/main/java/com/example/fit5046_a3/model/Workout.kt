package com.example.fit5046_a3.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "workout",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = ["uid"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Workout(
    @PrimaryKey(autoGenerate = true) val workoutId: Int = 0,
    val userId: Long,
    val workoutType: String,
    val workoutDate: String,
    val startTime: String,
    val endTime: String
)
