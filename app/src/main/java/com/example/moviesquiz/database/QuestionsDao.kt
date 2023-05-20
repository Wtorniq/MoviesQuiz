package com.example.moviesquiz.database

import androidx.room.*
import com.example.moviesquiz.database.entities.AnswerEntity
import com.example.moviesquiz.database.entities.CategoryEntity
import com.example.moviesquiz.database.entities.LevelEntity
import com.example.moviesquiz.database.entities.QuestionEntity

@Dao
interface QuestionsDao {
    @Transaction
    fun initAll(
        levelEntities: List<LevelEntity>,
        categoriesEntities: List<CategoryEntity>,
        questionsEntities: List<QuestionEntity>,
        answersEntities: List<AnswerEntity>
    ){
        initLevels(levelEntities)
        initCategories(categoriesEntities)
        initQuestions(questionsEntities)
        initAnswers(answersEntities)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun initLevels(levelEntities: List<LevelEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun initCategories(categoriesEntities: List<CategoryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun initQuestions(questionsEntities: List<QuestionEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun initAnswers(answersEntities: List<AnswerEntity>)

    @Query("SELECT * FROM LevelEntity")
    fun getLevels(): List<LevelEntity>

    @Query("SELECT * FROM CategoryEntity WHERE level_id = :levelId")
    fun getCategories(levelId: String): List<CategoryEntity>

    @Query("SELECT * FROM QuestionEntity WHERE category_id = :categoryId")
    fun getQuestions(categoryId: String): List<QuestionEntity>

    @Query("SELECT * FROM AnswerEntity WHERE question_id = :questionId")
    fun getAnswers(questionId: String): List<AnswerEntity>

    @Transaction
    fun setRightAnswer(
        levelId: Int,
        levelAnswersCounterValue: Int,
        categoryId: Int,
        categoryAnswersCounterValue: Int,
        questionId: Int,
        isAnswered: Boolean
    ){
        setLevelAnswersCounter(levelId, levelAnswersCounterValue)
        setCategoryAnswersCounter(categoryId, categoryAnswersCounterValue)
        setQuestionAsAnswered(questionId, isAnswered)
    }

    @Query("UPDATE LevelEntity SET answersCounter = :levelAnswersCounterValue WHERE id = :levelId")
    fun setLevelAnswersCounter(levelId: Int, levelAnswersCounterValue: Int)

    @Query("UPDATE CategoryEntity SET answersCounter = :categoryAnswersCounterValue WHERE id = :categoryId")
    fun setCategoryAnswersCounter(categoryId: Int, categoryAnswersCounterValue: Int)

    @Query("UPDATE QuestionEntity SET isAnswered = :isAnswered WHERE id = :questionId")
    fun setQuestionAsAnswered(questionId: Int, isAnswered: Boolean)

    @Query("DELETE FROM LevelEntity")
    fun deleteAll()
}