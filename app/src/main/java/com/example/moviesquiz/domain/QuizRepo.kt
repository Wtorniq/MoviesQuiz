package com.example.moviesquiz.domain

import com.example.moviesquiz.domain.entities.Answer
import com.example.moviesquiz.domain.entities.Category
import com.example.moviesquiz.domain.entities.Level
import com.example.moviesquiz.domain.entities.Question

interface QuizRepo {
    fun initDataBase()
    fun getLevelsList(): ArrayList<Level>
    fun getCategoriesList(levelId: String): ArrayList<Category>
    fun getQuestionsList(categoryId: String): ArrayList<Question>
    fun getAnswers(questionId: String): ArrayList<Answer>
    fun setAnsweredQuestion(
        levelId: String,
        levelCounter: Int,
        categoryId: String,
        categoryCounter: Int,
        questionId: String
    )
    fun setEnabledLevel(levelId: String)
    fun setEnabledCategory(categoryId: String)
    fun deleteAll()
}