package com.example.moviesquiz.domain

import com.example.moviesquiz.database.QuizDatabase
import com.example.moviesquiz.database.entities.*
import com.example.moviesquiz.domain.entities.Answer
import com.example.moviesquiz.domain.entities.Category
import com.example.moviesquiz.domain.entities.Level
import com.example.moviesquiz.domain.entities.Question

class QuizRepoImpl : QuizRepo {

    override fun initDataBase() {
        QuizDatabase.db.questionsDao().initAll(
            getInitialLevels(),
            getInitialCategories(),
            getInitialQuestions(),
            getInitialAnswerEntities()
        )
    }

    override fun getLevelsList(): ArrayList<Level> {
        val receivedList = QuizDatabase.db.questionsDao().getLevels()
        val returnedList = arrayListOf<Level>()
        receivedList.forEach {
            returnedList.add(convertEntityToLevel(it))
        }
        return returnedList
    }

    override fun getCategoriesList(levelId: String): ArrayList<Category> {
        val receivedList = QuizDatabase.db.questionsDao().getCategories(levelId)
        val returnedList = arrayListOf<Category>()
        receivedList.forEach {
            returnedList.add(convertEntityToCategory(it))
        }
        return returnedList
    }

    override fun getQuestionsList(categoryId: String): ArrayList<Question> {
        val receivedList = QuizDatabase.db.questionsDao().getQuestions(categoryId)
        val returnedList = arrayListOf<Question>()
        receivedList.forEach {
            returnedList.add(convertEntityToQuestion(it))
        }
        return returnedList
    }

    override fun getAnswers(questionId: String): ArrayList<Answer> {
        val receivedList = QuizDatabase.db.questionsDao().getAnswers(questionId)
        val returnedList = arrayListOf<Answer>()
        receivedList.forEach {
            returnedList.add(convertEntityToAnswer(it))
        }
        return returnedList
    }

    override fun setAnswered(
        levelId: String,
        levelCounter: Int,
        categoryId: String,
        categoryCounter: Int,
        questionId: String
    ) {
        QuizDatabase.db.questionsDao().setRightAnswer(levelId, levelCounter, categoryId, categoryCounter, questionId, true)
    }

    override fun deleteAll() {
        QuizDatabase.db.questionsDao().deleteAll()
    }

    private fun convertEntityToQuestion(questionEntity: QuestionEntity) = Question(
        questionEntity.id,
        questionEntity.screenshot,
        questionEntity.isAnswered
    )

    private fun convertEntityToCategory(categoryEntity: CategoryEntity) = Category(
        categoryEntity.id,
        categoryEntity.name,
        categoryEntity.answersCounter,
        categoryEntity.isEnabled
    )

    private fun convertEntityToLevel(levelEntity: LevelEntity) = Level(
        levelEntity.id,
        levelEntity.isEnabled,
        levelEntity.answersCounter
    )

    private fun convertEntityToAnswer(answerEntity: AnswerEntity) = Answer(
        answerEntity.text,
        answerEntity.possibleLetters,
        answerEntity.isRight
    )
}
