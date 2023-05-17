package com.example.moviesquiz.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesquiz.domain.QuizRepo
import com.example.moviesquiz.database.entities.QuestionEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repo: QuizRepo) : ViewModel() {
    private val levelsList = arrayListOf("easy", "normal", "hard")
    private val categoriesList = arrayListOf("зарубежные фильмы", "российские фильмы", "сериалы", "мульфильмы", "актеры", "классика")

    private var chosenLevel = 0
    private var chosenCategory = ""
    private var chosenQuestions = arrayListOf<QuestionEntity>()

    private var levelsLiveData = MutableLiveData<ArrayList<String>>()
    private var categoriesLiveData = MutableLiveData<ArrayList<String>>()
    private var questionsLiveData = MutableLiveData<ArrayList<QuestionEntity>>()
    fun getLevelsLiveData(): LiveData<ArrayList<String>?> = levelsLiveData
    fun getCategoriesLiveData(): LiveData<ArrayList<String>?> = categoriesLiveData
    fun getQuestionsLiveData(): LiveData<ArrayList<QuestionEntity>> = questionsLiveData



    fun getLevels() {
        levelsLiveData.postValue(levelsList)
    }
    fun getCategories() {
        categoriesLiveData.postValue(categoriesList)
    }
    fun getQuestions(){
        chosenQuestions = repo.getQuestionsList(chosenLevel, chosenCategory)
        questionsLiveData.postValue(chosenQuestions)
    }

    fun setDataToDatabase() {
        with(viewModelScope){
            launch(Dispatchers.IO) {
                repo.createDataBase()
            }
        }
    }

    fun setChosenLevel(lvl: Int){
        chosenLevel = lvl
    }

    fun setChosenCategory(category: String){
        chosenCategory = category
    }

/*    private fun getCategoriesFromFile(lvl: Int) {
        categoriesLiveData.postValue(api.readCat(lvl))
    }*/
}