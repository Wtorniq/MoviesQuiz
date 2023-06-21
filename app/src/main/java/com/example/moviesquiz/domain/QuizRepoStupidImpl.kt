package com.example.moviesquiz.domain

import com.example.moviesquiz.R
import com.example.moviesquiz.domain.entities.Answer
import com.example.moviesquiz.domain.entities.Category
import com.example.moviesquiz.domain.entities.Level
import com.example.moviesquiz.domain.entities.Question

class QuizRepoStupidImpl : QuizRepo {

    private val levels = arrayListOf(
        Level("1","Красный сентябрь","Здесь нужно выбрать правильный вариант ответа", R.drawable.cinema_1_img, R.color.green, true, 0),
        Level("2", "Желтый ноябрь","Здесь нужно собрать правильный ответ из букв", R.drawable.cinema_2_img, R.color.red, false, 0),
        Level("3", "Синий декабрь","Здесб нужно ввести правильный ответ", R.drawable.cinema_3_img, R.color.purple_200, false, 0)
    )

    private val categories = arrayListOf(
        Category("1.1", R.string.categoryNameForeignMovies,R.string.categoryDescGuessMovieFromScreenshot, 0, true),
        Category("1.2", R.string.categoryNameRussianMovies,R.string.categoryDescGuessMovieFromScreenshot, 0, false),
        Category("1.3", R.string.categoryNameShows, R.string.categoryDescGuessShowFromScreenshot, 0, false),
        Category("1.4", R.string.categoryNameCartoons,R.string.categoryDescGuessCartoonFromScreenshot, 0, false),
        Category("1.5", R.string.categoryNameActors,R.string.categoryDescGuessActorFromScreenshot, 0, false),
        Category("1.6", R.string.categoryNameClassic,R.string.categoryDescGuessMovieFromActors, 0, false)
    )

    private val questions = arrayListOf(
        Question("1.1.1", R.drawable._1_1_1, false),
        Question("1.1.2", R.drawable._1_1_2, false),
        Question("1.1.3", R.drawable._1_1_1, false),
        Question("1.1.4", R.drawable._1_1_1, false),
        Question("1.1.5", R.drawable._1_1_1, false),
        Question("1.1.6", R.drawable._1_1_1, false),
        Question("1.1.7", R.drawable._1_1_1, false),
        Question("1.1.8", R.drawable._1_1_1, false),
        Question("1.1.9", R.drawable._1_1_1, false),
        Question("1.1.10", R.drawable._1_1_1, false),
        Question("1.1.11", R.drawable._1_1_1, false),
        Question("1.1.12", R.drawable._1_1_1, false),
        Question("1.1.13", R.drawable._1_1_1, false),
        Question("1.1.14", R.drawable._1_1_1, false),
        Question("1.1.15", R.drawable._1_1_1, false),
        Question("1.1.16", R.drawable._1_1_1, false),
        Question("1.1.17", R.drawable._1_1_1, false),
        Question("1.1.18", R.drawable._1_1_1, false),
        Question("1.1.19", R.drawable._1_1_1, false),
        Question("1.1.20", R.drawable._1_1_1, false),
    )

    private val answers = arrayListOf(
        Answer( "Последний охотник на ведьм", false),
        Answer("Охотники на ведьм", true),
        Answer("Соколиный глаз", false),
        Answer("Мстители", false),
    )

    override fun initDataBase() {}

    override fun getLevelsList(): ArrayList<Level> = levels

    override fun getCategoriesList(levelId: String): ArrayList<Category> = categories

    override fun getQuestionsList(categoryId: String): ArrayList<Question> = questions

    override fun getAnswers(questionId: String): ArrayList<Answer> = answers

    override fun setAnsweredQuestion(
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

    override fun setEnabledLevel(levelId: String) {
        levels.forEach {
            if (it.id == levelId){
                it.isEnabled = true
            }
        }
    }

    override fun setEnabledCategory(categoryId: String) {
        categories.forEach {
            if (it.id == categoryId){
                it.isEnabled = true
            }
        }
    }

    override fun deleteAll() {

    }
}