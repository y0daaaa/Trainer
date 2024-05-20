package com.example.trainer.data


/**
 * використовується для збереження і отримання інформації про цю сесію користувача
 */
interface UserSession {
    /**
     * зберегти сесію для користувача
     */
    fun saveAuthUser(username: String)

    /**
     * отримати сесію користувача або повернути якщо не знайдено користувача
     */
    fun getAuthUser(): String?

    /**
     * закінчити сессію
     */
    fun logout()
}

