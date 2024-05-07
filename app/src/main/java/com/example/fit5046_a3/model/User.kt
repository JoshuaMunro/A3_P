package com.example.fit5046_a3.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val uid: Long = 0,
    val userName: String,
    val password: String,
    val email: String,
    val gender: String,
    val weight: Double,
    val height: Double,
    val dateOfBirth: String
)
