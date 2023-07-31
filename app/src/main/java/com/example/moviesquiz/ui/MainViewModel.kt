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
import com.example.moviesquiz.ui.states.CategoryState
import com.example.moviesquiz.ui.states.QuestionState
import com.example.moviesquiz.ui.states.QuestionsRoomState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

//const val COUNTER_FOR_ENABLE_CAT = 2
const val COUNTER_FOR_ENABLE_LEV = 5
class MainViewModel(private val repo: QuizRepo) : ViewModel() {


    private lateinit var currentLevel : Level
    private lateinit var currentCategory : Category
    private lateinit var currentQuestion : Question

    private var levels = arrayListOf<Level>()
    private var categories = arrayListOf<Category>()
    private var questions = arrayListOf<Question>()

    private var levelsLiveData = MutableLiveData<ArrayList<Level>>()
    private var categoriesLiveData = MutableLiveData<CategoryState>()
    private var questionsLiveData = MutableLiveData<QuestionsRoomState>()
    private var currentQuestionLiveData = MutableLiveData<QuestionState>()
    private var notificationDialogLiveData = MutableLiveData<SingleEvent<String>>()
    fun getLevelsLiveData(): LiveData<ArrayList<Level>> = levelsLiveData
    fun getCategoriesLiveData(): LiveData<CategoryState> = categoriesLiveData
    fun getQuestionsLiveData(): LiveData<QuestionsRoomState> = questionsLiveData
    fun getCurrentQuestionLiveData(): LiveData<QuestionState> = currentQuestionLiveData
    fun getNotificationDialogLiveData(): LiveData<SingleEvent<String>> = notificationDialogLiveData

    fun getLevels() = getLevelsFromRepo()
    fun getCategories() = getCategoriesFromRepo()
    fun getQuestions() = getQuestionsFromRepo()

    private fun getLevelsFromRepo() {
        viewModelScope.async (Dispatchers.IO) {
            levels = repo.getLevelsList()
            levelsLiveData.postValue(levels)
        }.onAwait
    }

    private fun getCategoriesFromRepo() {
        viewModelScope.launch(Dispatchers.IO) {
            categories = repo.getCategoriesList(currentLevel.id)
            categoriesLiveData.postValue(CategoryState(categories, currentLevel.color))
        }
    }

    private fun getQuestionsFromRepo() {
        viewModelScope.launch(Dispatchers.IO) {
            questions = repo.getQuestionsList(currentCategory.id)
            questionsLiveData.postValue(QuestionsRoomState(questions, currentLevel.color))
        }
    }

    private fun getAnswersFromRepo(): ArrayList<Answer> {
        val answers = ArrayList<Answer>()
        answers.addAll(repo.getAnswers(currentQuestion.id))
        return answers
    }

    fun getCurrentQuestion() {
        currentQuestionLiveData.postValue(QuestionState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            when(currentLevel.id){
                "1" -> {
                    currentQuestionLiveData.postValue(
                        QuestionState.SuccessEasyLevel(
                            currentQuestion,
                            getAnswersFromRepo()
                        )
                    )
                }
                "2" -> {
                    currentQuestionLiveData.postValue(
                        QuestionState.SuccessNormalLevel(
                            currentQuestion,
                            getAnswersFromRepo()[0]
                        )
                    )
                }
                "3" -> {
                    currentQuestionLiveData.postValue(
                        QuestionState.SuccessHardLevel(
                            currentQuestion,
                            getAnswersFromRepo()[0]
                        )
                    )
                }
            }
        }
    }

    fun setChosenLevel(lvl: Level){
        currentLevel = lvl
    }

    fun setChosenCategory(category: Category){
        currentCategory = category
    }

    fun setCurrentQuestion(question: Question) {
        currentQuestion = question
    }

    fun setAnswerAsRight() {
        viewModelScope.launch(Dispatchers.IO) {
            if (!currentQuestion.isAnswered){
                currentLevel.answersCounter += 1
                currentCategory.answersCounter += 1
//            checkNewEnabledCategories(currentLevel.answersCounter)
                checkNewEnabledLevels(currentLevel.answersCounter)
                repo.setAnsweredQuestion(
                    currentLevel.id,
                    currentLevel.answersCounter,
                    currentCategory.id,
                    currentCategory.answersCounter,
                    currentQuestion.id
                )
            }
        }
    }

/*    private fun checkNewEnabledCategories(counter: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val valuesToEnableNewCategory = arrayListOf<Int>()
            for (i in 0 until categories.size) {
                valuesToEnableNewCategory.add(COUNTER_FOR_ENABLE_CAT * i)
            }
            valuesToEnableNewCategory.forEach {
                if (counter == it) {
                    repo.setEnabledCategory(categories[valuesToEnableNewCategory.indexOf(it)].id)
                    notificationDialogLiveData.postValue(SingleEvent("Открылся новый зал!"))
                }
            }
        }
    }*/

    private fun checkNewEnabledLevels(counter: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (counter == COUNTER_FOR_ENABLE_LEV) {
                    val indexOfNextLevel = levels.indexOf(currentLevel) + 1
                    repo.setEnabledLevel(levels[indexOfNextLevel].id)
                    notificationDialogLiveData.postValue(SingleEvent("Открылся новый кинотеатр!"))
                }
            } catch (_: Throwable){}
        }
    }

    fun setNextQuestion() {
        viewModelScope.launch(Dispatchers.IO) {
            Thread.sleep(1000)
            try {
                val currentQuestionIndex = questions.indexOf(currentQuestion)
                val nextQuestion = questions[currentQuestionIndex + 1]
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
