package com.example.moviesquiz.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviesquiz.App

@Database(
    entities = [],
    version = 1,
    exportSchema = false
)
abstract class QuestionsDatabase : RoomDatabase() {
    abstract fun questionsDao(): QuestionsDao
    companion object {
        private const val DB_NAME = "questions_database.db"

        val db: QuestionsDatabase by lazy {
            Room.databaseBuilder(
                App.appInstance,
                QuestionsDatabase::class.java,
                DB_NAME
            ).build()
        }
    }
}