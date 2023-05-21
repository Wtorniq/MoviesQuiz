package com.example.moviesquiz.domain.entities

data class Category(
    val id: String,
    val name: String,
    var answersCounter: Int,
    var isEnabled: Boolean,
)
