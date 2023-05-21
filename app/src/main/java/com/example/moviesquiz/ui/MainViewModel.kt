package com.example.moviesquiz.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesquiz.domain.QuizRepo
import com.example.moviesquiz.domain.entities.Answer
import com.example.moviesquiz.domain.entities.Category
import com.example.moviesquiz.domain.entities.Level
import com.example.moviesquiz.domain.entities.Question
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repo: QuizRepo) : ViewModel() {
    private lateinit var chosenLevel : Level
    private lateinit var chosenCategory : Category
    private var chosenQuestions = arrayListOf<Question>()
    private lateinit var currentQuestion : Question

    private var levelsLiveData = MutableLiveData<ArrayList<Level>>()
    private var categoriesLiveData = MutableLiveData<ArrayList<Category>>()
    private var questionsLiveData = MutableLiveData<ArrayList<Question>>()
    private var currentQuestionLiveData = MutableLiveData<QuestionState>()
    fun getLevelsLiveData(): LiveData<ArrayList<Level>> = levelsLiveData
    fun getCategoriesLiveData(): LiveData<ArrayList<Category>> = categoriesLiveData
    fun getQuestionsLiveData(): LiveData<ArrayList<Question>> = questionsLiveData
    fun getCurrentQuestionLiveData(): LiveData<QuestionState> = currentQuestionLiveData

    fun getLevels() = getLevelsFromRepo()
    fun getCategories() = getCategoriesFromRepo()
    fun getQuestions() = getQuestionsFromRepo()

    private fun getLevelsFromRepo() {
        viewModelScope.launch(Dispatchers.IO) {
            val levelsList = repo.getLevelsList()
            levelsLiveData.postValue(levelsList)
        }
    }

    private fun getCategoriesFromRepo() {
        viewModelScope.launch(Dispatchers.IO) {
            val categoriesList = repo.getCategoriesList(chosenLevel.id)
            categoriesLiveData.postValue(categoriesList)
        }
    }

    private fun getQuestionsFromRepo() {
        viewModelScope.launch(Dispatchers.IO) {
            chosenQuestions = repo.getQuestionsList(chosenCategory.id)
            questionsLiveData.postValue(chosenQuestions)
        }
    }

    fun getCurrentQuestion() {
        val answers = ArrayList<Answer>()
        viewModelScope.launch(Dispatchers.IO) {
            answers.addAll(repo.getAnswers(currentQuestion.id))
            currentQuestionLiveData.postValue(QuestionState.Success(currentQuestion, answers))
        }
    }

    fun setChosenLevel(lvl: Level){
        chosenLevel = lvl
    }

    fun setChosenCategory(category: Category){
        chosenCategory = category
    }

    fun setCurrentQuestion(question: Question) {
        currentQuestion = question
    }

    fun setAnswerAsRight() {
        viewModelScope.launch(Dispatchers.IO) {
            val updateLevelCounter = chosenLevel.answersCounter + 1
            val updateCategoryCounter = chosenCategory.answersCounter + 1
            repo.setAnswered(
                chosenLevel.id,
                updateLevelCounter,
                chosenCategory.id,
                updateCategoryCounter,
                currentQuestion.id
            )
        }
    }

    fun setNextQuestion() {
        viewModelScope.launch(Dispatchers.IO) {
            Thread.sleep(2000)
            try {
                val currentQuestionIndex = chosenQuestions.indexOf(currentQuestion)
                val nextQuestion = chosenQuestions[currentQuestionIndex + 1]
                currentQuestion = nextQuestion
                getCurrentQuestion()
            } catch (e: Throwable) {
                currentQuestionLiveData.postValue(QuestionState.Error(e))
            }
        }
    }

    fun deleteData(){
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteAll()
        }
    }

    fun setDataToDatabase() {
        with(viewModelScope){
            launch(Dispatchers.IO) {
                repo.initDataBase()
            }
        }
    }
}
