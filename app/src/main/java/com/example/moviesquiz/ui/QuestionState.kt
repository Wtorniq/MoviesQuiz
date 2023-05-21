package com.example.moviesquiz.ui

import com.example.moviesquiz.domain.entities.Answer
import com.example.moviesquiz.domain.entities.Question

sealed class QuestionState {
    data class Success(val question: Question, val answers: ArrayList<Answer>): QuestionState()
    data class Error(val e: Throwable): QuestionState()
    object Loading: QuestionState()
}
