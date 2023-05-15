package com.example.moviesquiz.database

import androidx.room.*
import com.example.moviesquiz.database.entities.Question

@Dao
interface QuestionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(questionsList: List<Question>)

    @Query("SELECT * FROM QuestionEntity WHERE id = :id")
    fun getQuestion(id : Int) : Question

    @Update
    fun setQuestionAsAnswered(id: Int, isAnswered: Boolean)
}