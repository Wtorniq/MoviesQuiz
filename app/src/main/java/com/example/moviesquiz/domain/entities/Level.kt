package com.example.moviesquiz.domain.entities

data class Level(
    val id: String,
    val isEnabled: Boolean,
    val answersCounter: Int
)