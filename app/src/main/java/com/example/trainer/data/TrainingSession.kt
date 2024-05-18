package com.example.trainer.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "training_sessions")
data class TrainingSession(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val username: String,
    val units: Int,
    val date: Long
)
