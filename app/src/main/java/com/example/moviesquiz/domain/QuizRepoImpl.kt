package com.example.moviesquiz.domain

import com.example.moviesquiz.database.QuestionsDatabase
import com.example.moviesquiz.database.entities.Question

class QuizRepoImpl: QuizRepo {
    private val questions = listOf<Question>()
    override fun createDataBase() {
        QuestionsDatabase.db.questionsDao().insertAll(questions)
    }

    override fun getCertainQuestion(id: Int) {
        TODO("Not yet implemented")
    }

    override fun getPreviousQuestion(id: Int) {
        TODO("Not yet implemented")
    }

    override fun getNextQuestion(id: Int) {
        TODO("Not yet implemented")
    }

    override fun setIsAnswered(id: Int) {
        TODO("Not yet implemented")
    }

}