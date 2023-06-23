package com.example.moviesquiz.domain.entities

data class Category(
    val id: String,
    val name: Int,
    val description: Int,
    val icon: Int,
    var answersCounter: Int,
    var isEnabled: Boolean,
)
