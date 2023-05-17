package com.example.moviesquiz.domain

import com.example.moviesquiz.R
import com.example.moviesquiz.database.entities.QuestionEntity

class QuizRepoStupidImpl: QuizRepo {
    private val database = ArrayList<QuestionEntity>()
    override fun createDataBase() {
        Thread.sleep(3000)
        val mockQuestionsList = getMockQuestionsList()
        database.addAll(mockQuestionsList)
    }

    override fun getQuestionsList(level: Int, category: String): ArrayList<QuestionEntity> {
        Thread.sleep(3000)
        val returnedList = arrayListOf<QuestionEntity>()
        database.forEach { question ->
            if (question.level == level && question.category == category) {
                returnedList.add(question)
            }
        }
        return returnedList
    }

    override fun setAnswered(id: Long) {
        Thread.sleep(3000)
        database.forEach { question ->
            if (question.id == id) {question.isAnswered = true}
        }
    }

    private fun getMockQuestionsList(): ArrayList<QuestionEntity>{
        val mockListQuestions = arrayListOf<QuestionEntity>()
        for (i in 0..19){
            val a = QuestionEntity(
                i + 1L,
                1,
                "мультфильмы",
                "$i",
                arrayListOf("no", "no", "no"),
                null,
                R.drawable._023_01_19,
                false
            )
            mockListQuestions.add(a)
        }
        return mockListQuestions
    }
}