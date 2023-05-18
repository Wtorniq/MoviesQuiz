package com.example.moviesquiz.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesquiz.domain.QuizRepo
import com.example.moviesquiz.domain.Question
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repo: QuizRepo) : ViewModel() {
    private var chosenLevel = 0
    private var chosenCategory = ""
    private var chosenQuestions = arrayListOf<Question>()
    private lateinit var currentQuestion : Question

    private var questionsLiveData = MutableLiveData<ArrayList<Question>>()
    private var currentQuestionLiveData = MutableLiveData<Question>()
    fun getQuestionsLiveData(): LiveData<ArrayList<Question>> = questionsLiveData
    fun getCurrentQuestionLiveData(): LiveData<Question> = currentQuestionLiveData

    fun getQuestions() {
        chosenQuestions = repo.getQuestionsList(chosenLevel, chosenCategory)
        questionsLiveData.postValue(chosenQuestions)
    }

    fun getCurrentQuestion() {
        currentQuestionLiveData.postValue(currentQuestion)
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

    fun setCurrentQuestion(question: Question) {
        currentQuestion = question
    }

/*    private fun getCategoriesFromFile(lvl: Int) {
        categoriesLiveData.postValue(api.readCat(lvl))
    }*/
}