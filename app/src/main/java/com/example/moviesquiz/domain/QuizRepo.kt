package com.example.moviesquiz.domain

interface QuizRepo {
    fun createDataBase()
    fun getQuestionsList(level: Int, category: String): ArrayList<Question>
    fun setAnswered(id: Long)
}