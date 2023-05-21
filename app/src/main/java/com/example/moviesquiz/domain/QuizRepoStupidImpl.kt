package com.example.moviesquiz.domain

import com.example.moviesquiz.R
import com.example.moviesquiz.domain.entities.Answer
import com.example.moviesquiz.domain.entities.Category
import com.example.moviesquiz.domain.entities.Level
import com.example.moviesquiz.domain.entities.Question

class QuizRepoStupidImpl : QuizRepo {

    private val levels = arrayListOf(
        Level("1", true, 0),
        Level("2", false, 0),
        Level("3", false, 0)
    )

    private val categories = arrayListOf(
        Category("1.1", "зарубежные фильмы", 0, true),
        Category("1.2", "российские фильмы", 0, true),
        Category("1.3", "сериалы", 0, true),
        Category("1.4", "мультфильмы", 0, false),
        Category("1.5", "актеры", 0, false),
        Category("1.6", "классика", 0, false)
    )

    private val questions = arrayListOf(
        Question("1.1.1", R.drawable.__1_1_1, false),
        Question("1.1.2", R.drawable.__1_1_1, false),
        Question("1.1.3", R.drawable.__1_1_1, false),
        Question("1.1.4", R.drawable.__1_1_1, false),
        Question("1.1.5", R.drawable.__1_1_1, false),
        Question("1.1.6", R.drawable.__1_1_1, false),
        Question("1.1.7", R.drawable.__1_1_1, false),
        Question("1.1.8", R.drawable.__1_1_1, false),
        Question("1.1.9", R.drawable.__1_1_1, false),
        Question("1.1.10", R.drawable.__1_1_1, false),
        Question("1.1.11", R.drawable.__1_1_1, false),
        Question("1.1.12", R.drawable.__1_1_1, false),
        Question("1.1.13", R.drawable.__1_1_1, false),
        Question("1.1.14", R.drawable.__1_1_1, false),
        Question("1.1.15", R.drawable.__1_1_1, false),
        Question("1.1.16", R.drawable.__1_1_1, false),
        Question("1.1.17", R.drawable.__1_1_1, false),
        Question("1.1.18", R.drawable.__1_1_1, false),
        Question("1.1.19", R.drawable.__1_1_1, false),
        Question("1.1.20", R.drawable.__1_1_1, false),
    )

    private val answers = arrayListOf(
        Answer( "Последний охотник на ведьм", null, false),
        Answer("Охотники на ведьм", null, true),
        Answer("Соколиный глаз", null, false),
        Answer("Мстители", null, false),
    )

    override fun initDataBase() {
    }

    override fun getLevelsList(): ArrayList<Level> = levels

    override fun getCategoriesList(levelId: String): ArrayList<Category> = categories

    override fun getQuestionsList(categoryId: String): ArrayList<Question> = questions

    override fun getAnswers(questionId: String): ArrayList<Answer> = answers

    override fun setAnswered(
        levelId: String,
        levelCounter: Int,
        categoryId: String,
        categoryCounter: Int,
        questionId: String
    ) {
        levels.forEach {
            if (it.id == levelId){
                it.answersCounter = levelCounter
            }
        }
        categories.forEach {
            if (it.id == categoryId){
                it.answersCounter = categoryCounter
            }
        }
        questions.forEach {
            if (it.id == questionId){
                it.isAnswered = true
            }
        }
    }

    override fun deleteAll() {

    }
}