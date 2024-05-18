package com.example.trainer.data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UserSessionImpl @Inject constructor(@ApplicationContext context: Context) : UserSession {

    companion object {
        const val SESSION_STORAGE = "session"
        const val USERNAME = "username"
    }

    private val preferences = context.getSharedPreferences(SESSION_STORAGE, Context.MODE_PRIVATE)

    override fun saveAuthUser(username: String) {
        preferences.edit().putString(USERNAME, username).apply()
    }

    override fun getAuthUser(): String? {
        return preferences.getString(USERNAME, null)
    }

    override fun logout() {
        preferences.edit().remove(USERNAME).apply()
    }
}
