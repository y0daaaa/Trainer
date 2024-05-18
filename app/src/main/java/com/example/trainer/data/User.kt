package com.example.trainer.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val username: String,
    val email: String,
    val password: String,
    val age: String,
    val height: String,
    val weight: String
)
