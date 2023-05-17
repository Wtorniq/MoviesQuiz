package com.example.moviesquiz.domain

import com.example.moviesquiz.database.entities.QuestionEntity

interface QuizRepo {
    fun createDataBase()
    fun getQuestionsList(level: Int, category: String): ArrayList<QuestionEntity>
    fun setAnswered(id: Long)
}