package com.example.moviesquiz.domain.entities

data class Category(
    val id: String,
    val name: String,
    val description: String,
    var answersCounter: Int,
    var isEnabled: Boolean,
)
