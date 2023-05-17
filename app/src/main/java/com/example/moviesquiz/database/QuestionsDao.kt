package com.example.moviesquiz.database

import androidx.room.*
import com.example.moviesquiz.database.entities.QuestionEntity

@Dao
interface QuestionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(questionsList: List<QuestionEntity>)

    @Query("SELECT * FROM Question WHERE id = :id")
    fun getQuestion(level: Int, category: String) : ArrayList<QuestionEntity>

    @Query("UPDATE Question SET isAnswered = :isAnswered WHERE id = :id")
    fun setQuestionAsAnswered(id: Long, isAnswered: Boolean)
}