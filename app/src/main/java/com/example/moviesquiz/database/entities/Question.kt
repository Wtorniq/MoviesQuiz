package com.example.moviesquiz.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Question(
    @PrimaryKey val id: Int,
    val text: String,
    val answer: String,
    val wrongAnswers: ArrayList<String>?,
    val possibleLetters: ArrayList<String>?,
    val screenshot: Int,
    val isAnswered: Boolean
)
