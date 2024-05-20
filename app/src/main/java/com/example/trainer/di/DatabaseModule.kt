package com.example.trainer.di

import android.content.Context
import com.example.trainer.data.AppDatabase
import com.example.trainer.data.UserSession
import com.example.trainer.data.UsersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideUsersDao(database: AppDatabase): UsersDao {
        return database.getUsersDao()
    }

}