package com.example.moviesquiz.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Answer(
    val text: String,
    val isRight: Boolean,
): Parcelable
