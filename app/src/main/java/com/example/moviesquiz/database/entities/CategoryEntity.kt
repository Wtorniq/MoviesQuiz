package com.example.moviesquiz.database.entities

import androidx.room.*
import androidx.room.ForeignKey.Companion.CASCADE

@Entity(foreignKeys = [ForeignKey(
    LevelEntity::class,
    parentColumns = ["id"],
    childColumns = ["level_id"],
    onDelete = CASCADE,
)],
    indices = [Index("level_id")]
)
data class CategoryEntity(
    @PrimaryKey val id: String,
    val name: Int,
    val description: Int,
    val icon: Int,
    val answersCounter: Int,
    val isEnabled: Boolean,
    @ColumnInfo(name = "level_id") val levelId: String
)

fun getInitialCategories() = listOf(
    CategoryEntity("1.1", com.example.moviesquiz.R.string.categoryNameForeignMovies, com.example.moviesquiz.R.string.categoryDescGuessMovieFromScreenshot, com.example.moviesquiz.R.drawable.oscar, 0, true, "1"),
    CategoryEntity("1.2", com.example.moviesquiz.R.string.categoryNameRussianMovies,com.example.moviesquiz.R.string.categoryDescGuessMovieFromScreenshot, com.example.moviesquiz.R.drawable.matreshka,0, true, "1"),
    CategoryEntity("1.3", com.example.moviesquiz.R.string.categoryNameShows,com.example.moviesquiz.R.string.categoryDescGuessShowFromScreenshot, com.example.moviesquiz.R.drawable.tv_show, 0,true, "1"),
    CategoryEntity("1.4", com.example.moviesquiz.R.string.categoryNameCartoons,com.example.moviesquiz.R.string.categoryDescGuessCartoonFromScreenshot, com.example.moviesquiz.R.drawable.cartoon, 0,true,"1"),
    CategoryEntity("1.5", com.example.moviesquiz.R.string.categoryNameActors,com.example.moviesquiz.R.string.categoryDescGuessActorFromScreenshot, com.example.moviesquiz.R.drawable.masks, 0,true,"1"),
    CategoryEntity("1.6", com.example.moviesquiz.R.string.categoryNameClassic,com.example.moviesquiz.R.string.categoryDescGuessMovieFromScreenshot, com.example.moviesquiz.R.drawable.camera, 0,true, "1"),
    CategoryEntity("2.1", com.example.moviesquiz.R.string.categoryNameForeignMovies,com.example.moviesquiz.R.string.categoryDescGuessMovieFromScreenshot, com.example.moviesquiz.R.drawable.oscar, 0,true, "2"),
    CategoryEntity("2.2", com.example.moviesquiz.R.string.categoryNameRussianMovies,com.example.moviesquiz.R.string.categoryDescGuessMovieFromScreenshot, com.example.moviesquiz.R.drawable.matreshka, 0,true, "2"),
    CategoryEntity("2.3", com.example.moviesquiz.R.string.categoryNameShows,com.example.moviesquiz.R.string.categoryDescGuessShowFromScreenshot, com.example.moviesquiz.R.drawable.tv_show, 0,true, "2"),
    CategoryEntity("2.4", com.example.moviesquiz.R.string.categoryNameCartoons,com.example.moviesquiz.R.string.categoryDescGuessCartoonFromScreenshot, com.example.moviesquiz.R.drawable.cartoon, 0,true, "2"),
    CategoryEntity("2.5", com.example.moviesquiz.R.string.categoryNameActors,com.example.moviesquiz.R.string.categoryDescGuessActorFromScreenshot, com.example.moviesquiz.R.drawable.masks, 0,true, "2"),
    CategoryEntity("2.6", com.example.moviesquiz.R.string.categoryNameClassic,com.example.moviesquiz.R.string.categoryDescGuessMovieFromScreenshot, com.example.moviesquiz.R.drawable.camera, 0,true, "2"),
    CategoryEntity("3.1", com.example.moviesquiz.R.string.categoryNameForeignMovies,com.example.moviesquiz.R.string.categoryDescGuessMovieFromScreenshot, com.example.moviesquiz.R.drawable.oscar, 0,true, "3"),
    CategoryEntity("3.2", com.example.moviesquiz.R.string.categoryNameRussianMovies,com.example.moviesquiz.R.string.categoryDescGuessMovieFromScreenshot, com.example.moviesquiz.R.drawable.matreshka, 0,true, "3"),
    CategoryEntity("3.3", com.example.moviesquiz.R.string.categoryNameShows,com.example.moviesquiz.R.string.categoryDescGuessShowFromScreenshot, com.example.moviesquiz.R.drawable.tv_show, 0,true, "3"),
    CategoryEntity("3.4", com.example.moviesquiz.R.string.categoryNameCartoons,com.example.moviesquiz.R.string.categoryDescGuessCartoonFromScreenshot, com.example.moviesquiz.R.drawable.cartoon, 0,true, "3"),
    CategoryEntity("3.5", com.example.moviesquiz.R.string.categoryNameActors,com.example.moviesquiz.R.string.categoryDescGuessActorFromScreenshot, com.example.moviesquiz.R.drawable.masks, 0,true, "3"),
    CategoryEntity("3.6", com.example.moviesquiz.R.string.categoryNameClassic,com.example.moviesquiz.R.string.categoryDescGuessMovieFromScreenshot, com.example.moviesquiz.R.drawable.camera, 0,true, "3"),
    CategoryEntity("3.7", com.example.moviesquiz.R.string.categoryNameLocations, com.example.moviesquiz.R.string.categoryDescGuessMovieFromLocation, com.example.moviesquiz.R.drawable.location, 0,true, "3")
)
