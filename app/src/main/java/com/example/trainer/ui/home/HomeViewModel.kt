package com.example.trainer.ui.home

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
class HomeViewModel @Inject constructor(
    private val userDao: UsersDao,
    private val session: UserSession
) : ViewModel() {
    companion object {
        const val TAG = "HomeViewModel"
    }

    data class State(
        val userNotAuth: Boolean = false,
        val logoutDone: Boolean = false,
        val errorMessage: String? = null
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
            val user = userDao.fetchUser(username)
            if (user == null) {
                showMessage("Користувач не зареєстрований")
                signalUserNotAuth()
                return@launch
            }
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
