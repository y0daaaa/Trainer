package com.example.trainer.ui.signin

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
class SignInViewModel @Inject constructor(
    private val usersDao: UsersDao,
    private val session: UserSession,
) : ViewModel() {
    companion object {
        const val TAG = "SignInViewModel"
    }

    data class State(
        val errorMessage: String? = null,
        val authDone: Boolean = false,
        val isAuth: Boolean = false,
    )

    private val _stateFlow = MutableStateFlow(State())
    val stateFlow = _stateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            // проверка авторизации
            val isAuth = session.getAuthUser() != null
            _stateFlow.update { it.copy(isAuth = isAuth) }
        }
    }

    fun signIn(username: String, password: String) {
        viewModelScope.launch {
            // получаем пользователя и сравниваем пароли
            val user = usersDao.fetchUser(username)
            if (username == user?.username && password == user.password) {
                // авторизация успешна, сохраняем сессию
                session.saveAuthUser(username)
                // уведомляем о успешной авторизации
                signalAuthDone()
                return@launch
            }
            showMessage("We don`t have your account in base")
        }
    }

    // сбросить состояние ошибки
    fun userMessageShown() {
        _stateFlow.update { it.copy(errorMessage = null) }
    }

    fun showMessage(message: String) {
        _stateFlow.update { it.copy(errorMessage = message) }
    }

    private fun signalAuthDone() {
        _stateFlow.update { it.copy(authDone = true) }
    }
}