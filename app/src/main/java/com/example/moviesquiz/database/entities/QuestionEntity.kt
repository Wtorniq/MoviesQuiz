package com.example.moviesquiz.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuestionEntity(
    @PrimaryKey (autoGenerate = true) val id: Long,
    val level: Int,
    val category: String,
    val answer: String,
    val wrongAnswers: ArrayList<String>?,
    val possibleLetters: ArrayList<String>?,
    val screenshot: Int,
    var isAnswered: Boolean
)
