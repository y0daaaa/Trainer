package com.example.trainer.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainer.data.UserSession
import com.example.trainer.data.UsersDao
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
            if (username == null) {
                showMessage("Користувач не авторизований")
                signalUserNotAuth()
                return@launch
            }
            val user = usersDao.fetchUser(username)
            if (user == null) {
                showMessage("Користувач не зареєстрований")
                signalUserNotAuth()
                return@launch
            }

            _stateFlow.update { it.copy(username = user.username, email = user.email, age = user.age,
                weight = user.weight, height = user.height) }
        }
    }

    fun logout() {
        session.logout()
        _stateFlow.update { it.copy(logoutDone = true) }
    }

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
