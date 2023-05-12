package com.example.moviesquiz.domain

data class Question(
    val text: String,
    val answer: String,
    val wrongAnswers: ArrayList<String>,
    val screenshot: Int,
    val category: String,
    val level: Int
)
