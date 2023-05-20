package com.example.moviesquiz.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Question(
    val id: String,
    val screenshot: Int,
    val isAnswered: Boolean,
): Parcelable
