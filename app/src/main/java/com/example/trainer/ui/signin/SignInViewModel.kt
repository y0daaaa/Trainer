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
    private val session: UserSession
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

            val isAuth = session.getAuthUser() != null
            _stateFlow.update { it.copy(isAuth = isAuth) }
        }
    }

    fun signIn(username: String, password: String) {
        viewModelScope.launch {
            val user = usersDao.fetchUser(username)

            if (username == user?.username && password == user.password) {
                session.saveAuthUser(username)

                signalAuthDone()
                return@launch
            }
            showMessage("У нашій базі нема такого користувача")
        }
    }

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
