package com.example.moviesquiz.domain

import com.example.moviesquiz.R

class QuizRepoStupidImpl : QuizRepo {
    private val database = ArrayList<Question>()
    override fun createDataBase() {
        val mockQuestionsList = getMockQuestionsList()
        database.addAll(mockQuestionsList)
    }

    override fun getQuestionsList(level: Int, category: String): ArrayList<Question> {
        val returnedList = arrayListOf<Question>()
        database.forEach { question ->
            if (question.level == level && question.category == category) {
                returnedList.add(question)
            }
        }
        return returnedList
    }

    override fun setAnswered(id: Long) {
        database.forEach { question ->
            if (question.id == id) {
                question.isAnswered = true
            }
        }
    }

    private fun getMockQuestionsList(): ArrayList<Question> {
        val mockListQuestions = arrayListOf<Question>()

        for (i in 0..19) {
            val a = Question(
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