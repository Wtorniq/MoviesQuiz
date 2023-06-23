package com.example.moviesquiz.ui.states

import com.example.moviesquiz.domain.entities.Question

data class QuestionsRoomState(
    val questions: ArrayList<Question>,
    val color: Int
)
