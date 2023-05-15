package com.example.moviesquiz.domain

import com.example.moviesquiz.database.entities.Question

interface QuizRepo {
    fun createDataBase()
    fun getCertainQuestion(id: Int)
    fun getPreviousQuestion(id: Int)
    fun getNextQuestion(id: Int)
    fun setIsAnswered(id: Int)
}