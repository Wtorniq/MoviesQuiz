package com.example.moviesquiz.domain.entities

data class Level(
    val id: String,
    var isEnabled: Boolean,
    var answersCounter: Int
)