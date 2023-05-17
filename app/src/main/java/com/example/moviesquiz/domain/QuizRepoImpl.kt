package com.example.moviesquiz.domain

import com.example.moviesquiz.database.QuestionsDatabase
import com.example.moviesquiz.database.entities.QuestionEntity

class QuizRepoImpl: QuizRepo {
    private val questions = arrayListOf<QuestionEntity>()
    override fun createDataBase() {
        QuestionsDatabase.db.questionsDao().insertAll(questions)
    }

    override fun getQuestionsList(level: Int, category: String): ArrayList<QuestionEntity> {
        return QuestionsDatabase.db.questionsDao().getQuestion(level, category)
    }

    override fun setAnswered(id: Long) {
        QuestionsDatabase.db.questionsDao().setQuestionAsAnswered(id, true)
    }

    private fun convertQuestionToEntity(question: Question) = QuestionEntity(
        question.id,
        question.level,
        question.category,
        question.answer,
        question.wrongAnswers,
        question.possibleLetters,
        question.screenshot,
        question.isAnswered
    )

    private fun convertEntityToQuestion(questionEntity: QuestionEntity) = Question(
        questionEntity.id,
        questionEntity.level,
        questionEntity.category,
        questionEntity.answer,
        questionEntity.wrongAnswers,
        questionEntity.possibleLetters,
        questionEntity.screenshot,
        questionEntity.isAnswered
    )
}