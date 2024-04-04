package com.example.trainer.data


/**
 * Используется для сохранения и получении информации о текущей сессии пользователя
 */
interface UserSession {
    /**
     * Сохранить сессию для пользователя
     */
    fun saveAuthUser(username: String)

    /**
     * Получить сессию пользователя
     * @return имя пользователя, или null если пользователь не авторизован
     */
    fun getAuthUser(): String?

    /**
     * Закончить сессию
     */
    fun logout()
}

