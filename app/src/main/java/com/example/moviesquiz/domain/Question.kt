package com.example.moviesquiz.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Question(
    val id: Long,
    val level: Int,
    val category: String,
    val answer: String,
    val wrongAnswers: ArrayList<String>?,
    val possibleLetters: ArrayList<String>?,
    val screenshot: Int,
    var isAnswered: Boolean
): Parcelable
