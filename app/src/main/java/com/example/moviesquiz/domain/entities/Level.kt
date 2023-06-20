package com.example.moviesquiz.domain.entities

data class Level(
    val id: String,
    val name: String,
    val description: String,
    val image: Int,
    val color: Int,
    var isEnabled: Boolean,
    var answersCounter: Int
)