package com.example.trainer.data

interface UserSession {
    fun saveAuthUser(username: String)
    fun getAuthUser(): String?
    fun logout()
}
