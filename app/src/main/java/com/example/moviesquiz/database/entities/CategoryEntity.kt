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
    val name: String,
    val description: String,
    val answersCounter: Int,
    val isEnabled: Boolean,
    @ColumnInfo(name = "level_id") val levelId: String
)

fun getInitialCategories() = listOf(
    CategoryEntity("1.1", "1cat1","", 0, true, "1"),
    CategoryEntity("1.2", "1cat2","", 0, true, "1"),
    CategoryEntity("1.3", "1cat3","", 0, true, "1"),
    CategoryEntity("1.4", "1cat4","", 0, false, "1"),
    CategoryEntity("1.5", "1cat5","", 0, false, "1"),
    CategoryEntity("1.6", "1cat6","", 0, false, "1"),
    CategoryEntity("2.1", "2cat1","", 0, true, "2"),
    CategoryEntity("2.2", "2cat2","", 0, true, "2"),
    CategoryEntity("2.3", "2cat3","", 0, true, "2"),
    CategoryEntity("2.4", "2cat4","", 0, false, "2"),
    CategoryEntity("2.5", "2cat5","", 0, false, "2"),
    CategoryEntity("2.6", "2cat6","", 0, false, "2"),
    CategoryEntity("3.1", "3cat1","", 0, true, "3"),
    CategoryEntity("3.2", "3cat2","", 0, true, "3"),
    CategoryEntity("3.3", "3cat3","", 0, true, "3"),
    CategoryEntity("3.4", "3cat4","", 0, false, "3"),
    CategoryEntity("3.5", "3cat5","", 0, false, "3"),
    CategoryEntity("3.6", "3cat6","", 0, false, "3")
)
