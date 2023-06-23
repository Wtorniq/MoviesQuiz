package com.example.moviesquiz.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviesquiz.R

@Entity
data class LevelEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val image: Int,
    val color: Int,
    val isEnabled: Boolean,
    val answersCounter: Int
)

fun getInitialLevels() = listOf(
    LevelEntity(
        "1",
        "Красный сентябрь",
        "Здесь нужно выбрать правильный вариант ответа",
        R.drawable.cinema_2_img,
        R.color.lvl_1_color,
        true,
        0),
    LevelEntity("2",
        "Желтый ноябрь",
        "Здесь нужно собрать правильный ответ из букв",
        R.drawable.cinema_3_img,
        R.color.lvl_2_color,
        false,
        0),
    LevelEntity("3",
        "Синий декабрь",
        "Здесб нужно ввести правильный ответ",
        R.drawable.cinema_1_img,
        R.color.lvl_3_color,
        false,
        0)
)
