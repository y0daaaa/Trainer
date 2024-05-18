package com.example.trainer.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [User::class, TrainingSession::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getUsersDao(): UsersDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                val tempInstance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "trainer.db"
                )
                    .fallbackToDestructiveMigration()
                    .addMigrations(MIGRATION_1_2) // Додавання міграції
                    .build()
                instance = tempInstance
                tempInstance
            }
        }

        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    "CREATE TABLE IF NOT EXISTS training_sessions (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                            "username TEXT NOT NULL, " +
                            "units INTEGER NOT NULL, " +
                            "date INTEGER NOT NULL)"
                )
            }
        }
    }
}
