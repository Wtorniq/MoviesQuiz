package com.example.moviesquiz.ui

import com.example.moviesquiz.domain.entities.Answer
import com.example.moviesquiz.domain.entities.Question

sealed class QuestionState {
    data class SuccessEasyLevel(val question: Question, val answers: ArrayList<Answer>): QuestionState()
    data class SuccessNormalLevel(val question: Question, val answer: Answer): QuestionState()
    data class SuccessHardLevel(val question: Question, val answer: Answer): QuestionState()
    data class Error(val e: Throwable): QuestionState()
    object Loading: QuestionState()
}
