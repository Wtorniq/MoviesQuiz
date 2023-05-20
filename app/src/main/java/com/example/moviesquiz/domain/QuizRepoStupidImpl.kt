package com.example.moviesquiz.domain

import com.example.moviesquiz.R
import com.example.moviesquiz.domain.entities.Answer
import com.example.moviesquiz.domain.entities.Category
import com.example.moviesquiz.domain.entities.Level
import com.example.moviesquiz.domain.entities.Question

class QuizRepoStupidImpl : QuizRepo {
    override fun initDataBase() {
        TODO("Not yet implemented")
    }

    override fun getLevelsList(): ArrayList<Level> {
        TODO("Not yet implemented")
    }

    override fun getCategoriesList(levelId: String): ArrayList<Category> {
        TODO("Not yet implemented")
    }

    override fun getQuestionsList(categoryId: String): ArrayList<Question> {
        TODO("Not yet implemented")
    }

    override fun getAnswers(questionId: String): ArrayList<Answer> {
        TODO("Not yet implemented")
    }

    override fun setAnswered(questionId: String) {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {
        TODO("Not yet implemented")
    }
}