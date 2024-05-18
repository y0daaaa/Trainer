package com.example.trainer.di

import com.example.trainer.data.UserSession
import com.example.trainer.data.UserSessionImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UserSessionModule {
    @Binds
    fun bindUserSession(impl: UserSessionImpl): UserSession
}
