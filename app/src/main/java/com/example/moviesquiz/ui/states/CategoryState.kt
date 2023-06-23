package com.example.moviesquiz.ui.states

import com.example.moviesquiz.domain.entities.Category

data class CategoryState(
    val categories: ArrayList<Category>,
    val color: Int
)
