package com.example.moviesquiz.database

import androidx.room.*
import com.example.moviesquiz.App
import com.example.moviesquiz.database.entities.AnswerEntity
import com.example.moviesquiz.database.entities.CategoryEntity
import com.example.moviesquiz.database.entities.LevelEntity
import com.example.moviesquiz.database.entities.QuestionEntity

@Database(
    entities = [LevelEntity::class, CategoryEntity::class, QuestionEntity::class, AnswerEntity::class],
    version = 3,
    exportSchema = false
)
abstract class QuizDatabase : RoomDatabase() {
    abstract fun questionsDao(): QuestionsDao
    companion object {
        private const val DB_NAME = "questions_database.db"

        val db: QuizDatabase by lazy {
            Room.databaseBuilder(
                App.appInstance,
                QuizDatabase::class.java,
                DB_NAME
            ).fallbackToDestructiveMigration().build()
        }
    }
}
