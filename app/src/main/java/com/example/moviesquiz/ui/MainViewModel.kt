package com.example.moviesquiz.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesquiz.R
import com.example.moviesquiz.domain.QuizRepo
import com.example.moviesquiz.database.entities.Question

class MainViewModel(private val repo: QuizRepo) : ViewModel() {
    private val levelsList = arrayListOf("easy", "normal", "hard")
    private val categoriesList = arrayListOf("зарубежные фильмы", "российские фильмы", "сериалы", "мульфильмы", "актеры", "классика")

    private var chosenLevel = 0
    private var chosenCategory = ""
    private var certainQuestions = arrayListOf<Question>()

    private var levelsLiveData = MutableLiveData<ArrayList<String>>()
    private var categoriesLiveData = MutableLiveData<ArrayList<String>>()
    private var questionsLiveData = MutableLiveData<ArrayList<Question>>()
    fun getLevelsLiveData(): LiveData<ArrayList<String>?> = levelsLiveData
    fun getCategoriesLiveData(): LiveData<ArrayList<String>?> = categoriesLiveData
    fun getQuestionsLiveData(): LiveData<ArrayList<Question>> = questionsLiveData

    private fun getMockQuestionsList(): ArrayList<Question>{
        val mockListQuestions = arrayListOf<Question>()
        for (i in 0..19){
            val a = Question(
                i + 1,
                "The question №${i+1}",
                "yes",
                arrayListOf("no", "no", "no"),
                null,
                R.drawable._023_01_19,
                false
            )
            mockListQuestions.add(a)
        }
        return mockListQuestions
    }

    fun getLevels() {
        levelsLiveData.postValue(levelsList)
    }
    fun getCategories() {
        categoriesLiveData.postValue(categoriesList)
    }
    fun getQuestions(category: String){
        questionsLiveData.postValue(getMockQuestionsList())
    }

    fun setDataToDatabase() {
        repo.createDataBase()
    }

    fun setChosenLevel(lvl: Int){
        chosenLevel = lvl
    }

/*    private fun getCategoriesFromFile(lvl: Int) {
        categoriesLiveData.postValue(api.readCat(lvl))
    }*/
}