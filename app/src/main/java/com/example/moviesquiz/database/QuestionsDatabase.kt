package com.example.moviesquiz.database

import androidx.room.*
import com.example.moviesquiz.App
import com.example.moviesquiz.database.entities.QuestionEntity

@Database(
    entities = [QuestionEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(StringListConverter::class)
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

class StringListConverter {
    @TypeConverter
    fun fromStringList(list: ArrayList<String>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun toStringList(data: String): ArrayList<String> {
        return ArrayList(data.split(","))
    }
}