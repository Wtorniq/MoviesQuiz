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
    LevelEntity("1","Красный сентябрь","Здесь нужно выбрать правильный вариант ответа", R.drawable.craiyon_201326_movie_theater_building_in_flat_design_removebg_preview, R.color.green, true, 0),
    LevelEntity("2","Желтый ноябрь","Здесь нужно собрать правильный ответ из букв", R.drawable.craiyon_201336_movie_theater_building_in_flat_design_removebg_preview, R.color.red, false, 0),
    LevelEntity("3","Синий декабрь","Здесб нужно ввести правильный ответ", R.drawable.craiyon_201302_movie_theater_building_in_flat_design_removebg_preview, R.color.purple_200, false, 0)
)
