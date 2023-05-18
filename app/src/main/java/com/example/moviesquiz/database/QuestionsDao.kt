package com.example.moviesquiz.database

import androidx.room.*
import com.example.moviesquiz.database.entities.QuestionEntity

@Dao
interface QuestionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(questionsList: List<QuestionEntity>)

    @Query("SELECT * FROM QuestionEntity WHERE level = :level AND category = :category")
    fun getQuestion(level: Int, category: String) : List<QuestionEntity>

    @Query("UPDATE QuestionEntity SET isAnswered = :isAnswered WHERE id = :id")
    fun setQuestionAsAnswered(id: Long, isAnswered: Boolean)
}