package com.example.moviesquiz.domain.entities

data class Category(
    val id: String,
    val name: String,
    val answersCounter: Int,
    val isEnabled: Boolean,
)
