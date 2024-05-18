package com.example.trainer.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainer.data.UserSession
import com.example.trainer.data.UsersDao
import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val usersDao: UsersDao,
    private val session: UserSession,
) : ViewModel() {
    companion object {
        const val TAG = "ProfileViewModel"
    }

    data class State(
        val userNotAuth: Boolean = false,
        val logoutDone: Boolean = false,
        val errorMessage: String? = null,
        val username: String = "",
        val email: String = "",
        val age: String = "",
        val height: String = "",
        val weight: String = ""
    )

    private val _stateFlow = MutableStateFlow(State())
    val stateFlow = _stateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            val username = session.getAuthUser()
            Log.d(TAG, "AuthUser: $username")
            if (username == null) {
                showMessage("Пользователь не авторизован")
                signalUserNotAuth()
                return@launch
            }
            // Отримання всіх користувачів для діагностики
            val allUsers = usersDao.getAllUsers()
            Log.d(TAG, "All users: $allUsers")

            // Отримання інформації про користувача
            val user = usersDao.fetchUser(username)
            Log.d(TAG, "Fetched user: $user")
            if (user == null) {
                showMessage("Пользователь не зарегистрирован")
                signalUserNotAuth()
                return@launch
            }

            _stateFlow.update {
                it.copy(
                    username = user.username,
                    email = user.email,
                    age = user.age,
                    weight = user.weight,
                    height = user.height
                )
            }
        }
    }

    fun logout() {
        session.logout()
        _stateFlow.update { it.copy(logoutDone = true) }
    }

    // зкинути стан помилки
    fun userMessageShown() {
        _stateFlow.update { it.copy(errorMessage = null) }
    }

    private fun showMessage(message: String) {
        _stateFlow.update { it.copy(errorMessage = message) }
    }

    private fun signalUserNotAuth() {
        _stateFlow.update { it.copy(userNotAuth = true) }
    }
}
