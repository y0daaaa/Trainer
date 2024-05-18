package com.example.trainer.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [User::class, TrainingSession::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getUsersDao(): UsersDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "trainer.db"
                    )
                        .fallbackToDestructiveMigration()
                        .addMigrations(MIGRATION_1_2)
                        .build()
                }
            }
            return instance!!
        }

        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    "CREATE TABLE IF NOT EXISTS User_New (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                            "email TEXT, " +
                            "login TEXT, " +
                            "password TEXT, " +
                            "age TEXT, " +
                            "height TEXT, " +
                            "weight TEXT)"
                )

                db.execSQL("INSERT INTO User_New (id, email, login, password) SELECT id, email, login, password FROM User")

                db.execSQL("DROP TABLE IF EXISTS User")

                db.execSQL("ALTER TABLE User_New RENAME TO User")
            }
        }

    }
}