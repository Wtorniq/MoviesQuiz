package com.example.moviesquiz.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LevelEntity(
    @PrimaryKey val id: String,
    val isEnabled: Boolean,
    val answersCounter: Int
){
}

fun getInitialLevels() = listOf(
    LevelEntity("1", true, 0),
    LevelEntity("2", false, 0),
    LevelEntity("3", false, 0)
)
